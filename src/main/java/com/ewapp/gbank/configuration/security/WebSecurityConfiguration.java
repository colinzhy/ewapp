package com.ewapp.gbank.configuration.security;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Web application security configuration.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Inject
  private DataSource dataSource;

  @Inject
  private UserDetailsService userDetailsService;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // build DaoAuthenticationProvider manually to enable displaying "User Not Found" error, otherwise it always
    // displays "Bad Credential"
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setHideUserNotFoundExceptions(false);
    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setUserDetailsService(userDetailsService);
    auth.authenticationProvider(authProvider);
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);

    //@formatter:off
    httpSecurity//
      .formLogin()//
        .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())// redirect to saved request after login successfully
        .loginPage("/login")//
          .permitAll()//
        .failureUrl("/login-error")//
        .defaultSuccessUrl("/")//
        .and()//
      .logout()//
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//
          .logoutSuccessUrl("/logged-out")//
            .permitAll()//
        .and()//
      .rememberMe()//
        .tokenRepository(tokenRepository)//
        .tokenValiditySeconds(1000000)//
        .and()//
      .sessionManagement()//
        .maximumSessions(1)//only allow user to login once a time
          .and()//
        .sessionFixation()//
          .migrateSession()//
          .and()//
      .authorizeRequests()//
        .anyRequest()//
          .authenticated();
      //@formatter:on

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()//
        .antMatchers(//
            "/dist/**", //
            "/bootstrap/**", //
            "/plugins/**", //
            "/images/**", //
            "/css/**", //
            "/js/**"//
    );
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
