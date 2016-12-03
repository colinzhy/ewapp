package com.ewapp.gbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.ewapp.gbank")
public class GBankApplication {

  public static void main(String[] args) {
    SpringApplication.run(GBankApplication.class, args);
  }

}
