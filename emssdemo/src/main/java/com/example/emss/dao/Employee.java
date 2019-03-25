package com.example.emss.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.emss.auth.Role;
import com.example.emss.auth.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String type;

  @JsonIgnore
  @OneToOne(targetEntity = UserDetailsImpl.class)
  @JoinColumn(name = "security_users_id")
  private UserDetailsImpl userDetails;

  public Long getId () {

    return id;
  }

  public void setId (Long id) {

    this.id = id;
  }

  public String getFirstName () {

    return firstName;
  }

  public void setFirstName (String firstName) {

    this.firstName = firstName;
  }

  public String getLastName () {

    return lastName;
  }

  public void setLastName (String lastName) {

    this.lastName = lastName;
  }

  public String getType () {

    return type;
  }

  public void setType (String type) {

    this.type = type;
  }

  public UserDetailsImpl getUserDetails () {

    return userDetails;
  }

  public void setUserDetails (UserDetailsImpl userDetails) {

    this.userDetails = userDetails;
  }

  @JsonIgnore
  public void addRole (Role role) {

    this.userDetails.addAuthorities (role);
  }

  @JsonIgnore
  public List<String> getRoleNames () {

    List<String> names = new ArrayList<> ();
    Collection<? extends GrantedAuthority> roles = this.userDetails.getAuthorities ();
    if (!roles.isEmpty ()) {
     names = roles.stream ().flatMap (role -> Stream.of (((GrantedAuthority) role).getAuthority ()))
          .collect (Collectors.toList ());
    }
    return names;
  }
}
