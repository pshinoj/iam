package com.example.emss;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class BasicRequestMatcher implements RequestMatcher {
  @Override
  public boolean matches (HttpServletRequest request) {

    return  (request.getRequestURI ().contains ("/basic/"));
  }
}
