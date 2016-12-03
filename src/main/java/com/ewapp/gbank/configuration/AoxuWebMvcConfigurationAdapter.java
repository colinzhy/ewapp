package com.ewapp.gbank.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AoxuWebMvcConfigurationAdapter extends WebMvcConfigurerAdapter {

  @Autowired
  private ITemplateResolver templateResolver;

  /**
   * create template engine.
   * 
   * @return template engine
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(templateResolver);
    // Add Layout dialect
    engine.addDialect(new LayoutDialect());
    // Add spring security dialect
    engine.addDialect(springSecurityDialect());
    return engine;
  }

  @Bean
  public SpringSecurityDialect springSecurityDialect() {
    return new SpringSecurityDialect();
  }

}
