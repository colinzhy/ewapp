package com.ewapp.gbank.configuration.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Web application security configuration.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  protected DataSource dataSource;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
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
          .tokenRepository(persistentTokenRepository())//
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

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    return tokenRepository;
  }

}
