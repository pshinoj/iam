package com.example.emss.auth;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "security_users")
public class UserDetailsImpl implements UserDetails {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  Long id;
  @Column(name = "username", unique = true)
  String userName;
  String password;
  boolean enabled;

  @JsonIgnore
  @ManyToMany (targetEntity = Role.class)
  Collection<Role> authorities;

  public UserDetailsImpl () {

  }

  @Transient
  final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder (4);


  public UserDetailsImpl (String userName, String password) {

    this.userName = userName;
    this.password = bCryptPasswordEncoder.encode (password);
    this.enabled = true;
  }

  public Long getId () {

    return id;
  }

  public void setId (Long id) {

    this.id = id;
  }

  public void setUserName (String userName) {

    this.userName = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities () {

    return authorities;
  }

  public void setAuthorities (Collection<Role> roles) {

    if (authorities == null) {
      this.authorities = new ArrayList<> ();
    }
    this.authorities = roles;
  }

  @Transient
  public Collection<Role> getRoles () {

    Collection<Role> roles = new ArrayList<> ();
    if (authorities == null) {

      return roles;
    }
    for (Role role : authorities) {
      Role x = new Role ();
      x.setId (role.getId ());
      x.setName (role.getName ().replace ("ROLE_", ""));
      roles.add (x);
    }
    return roles;
  }

  @Transient
  public void addAuthorities (Role role) {

    if (authorities == null) {
      this.authorities = new ArrayList<> ();
    }
    this.authorities.add (role);
  }

  public String getPassword () {

    return password;
  }

  @Override
  public String getUsername () {

    return userName;
  }

  @Override
  public boolean isAccountNonExpired () {

    return true;
  }

  @Override
  public boolean isAccountNonLocked () {

    return true;
  }

  @Override
  public boolean isCredentialsNonExpired () {

    return true;
  }

  public void setPassword (String password) {

    this.password = bCryptPasswordEncoder.encode (password);
  }

  public boolean isEnabled () {

    return enabled;
  }

  public void setEnabled (boolean enabled) {

    this.enabled = enabled;
  }
}
