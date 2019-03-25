package com.example.emss;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class BearerSecurityConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
        .httpBasic ().disable ()
        .authorizeRequests ()
        .antMatchers ("/bearer/employees").authenticated ()
        .antMatchers ("/bearer/employess/**").authenticated ()
        //        .and ()
        //        .authorizeRequests ()
        //          .antMatchers ("/**").permitAll ()
        .and ()
        .csrf ().disable ();
  }
}


