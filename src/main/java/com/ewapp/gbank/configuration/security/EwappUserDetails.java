package com.ewapp.gbank.configuration.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ewapp.gbank.model.User;

public class EwappUserDetails implements UserDetails {

  private static final long serialVersionUID = 758901163060223566L;

  private final User user;

  private final Set<GrantedAuthority> authorities = new HashSet<>();

  public EwappUserDetails(User user) {
    this.user = user;
    // TODO: initialize authorities here
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }

}
