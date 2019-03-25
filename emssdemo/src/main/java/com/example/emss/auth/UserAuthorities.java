package com.example.emss.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_authorities")
public class UserAuthorities {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Long id;
  @Column(name = "username")
  String userName;
  String authority;

  public UserAuthorities () {}

  public UserAuthorities (String name, String authority) {

    this.userName = name;
    this.authority = authority;
  }

  public Long getId () {

    return id;
  }

  public void setId (Long id) {

    this.id = id;
  }

  public String getUserName () {

    return userName;
  }

  public void setUserName (String userName) {

    this.userName = userName;
  }

  public String getAuthority () {

    return authority;
  }

  public void setAuthority (String authority) {

    this.authority = authority;
  }
}
