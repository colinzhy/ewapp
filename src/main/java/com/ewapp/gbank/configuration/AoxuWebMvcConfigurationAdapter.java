package com.ewapp.gbank.configuration;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesCriteriasMethodArgumentResolver;
import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class AoxuWebMvcConfigurationAdapter extends WebMvcConfigurerAdapter {

  @Autowired
  private ITemplateResolver templateResolver;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new DatatablesCriteriasMethodArgumentResolver());
  }

  @Bean
  public DandelionDialect dandelionDialect() {
    return new DandelionDialect();
  }

  @Bean
  public DataTablesDialect dataTablesDialect() {
    return new DataTablesDialect();
  }

  @Bean
  public Filter dandelionFilter() {
    return new DandelionFilter();
  }

  @Bean
  public ServletRegistrationBean dandelionServletRegistrationBean() {
    return new ServletRegistrationBean(new DandelionServlet(), "/dandelion-assets/*");
  }

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
    // engine.addDialect(springSecurityDialect());
    // Add Dandelion and Datatables dialects
    engine.addDialect(dandelionDialect());
    engine.addDialect(new DataTablesDialect());
    return engine;
  }

  // @Bean
  // public SpringSecurityDialect springSecurityDialect() {
  // return new SpringSecurityDialect();
  // }

}
