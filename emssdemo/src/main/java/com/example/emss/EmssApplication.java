package com.example.emss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
public class EmssApplication {

  public static void main (String[] args) {

    SpringApplication.run (EmssApplication.class, args);
  }

  @Bean
  public PasswordEncoder passwordEncoder () {

    return new BCryptPasswordEncoder (4);
  }

}
