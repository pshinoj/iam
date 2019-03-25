package com.example.emss;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
@Order (2)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  DataSource dataSource;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  protected void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {

    auth.jdbcAuthentication ()
        .dataSource (dataSource)
        .usersByUsernameQuery (
            "select username, password, enabled from security_users where " + "username = ?")
        .authoritiesByUsernameQuery (
            "select username, authority from user_authorities where " + "username = ?")
        .passwordEncoder (passwordEncoder);
  }

  @Override
  protected void configure (HttpSecurity httpSecurity) throws Exception {

    httpSecurity.requestMatcher (new BasicRequestMatcher ())
        .authorizeRequests ()
          .antMatchers ("/basic/employees")
            .authenticated ()
          .antMatchers ("/basic/employees/**")
            .authenticated ()
        .and ()
          .httpBasic ()
        .and ()
          .csrf ()
            .disable ();
  }
}
