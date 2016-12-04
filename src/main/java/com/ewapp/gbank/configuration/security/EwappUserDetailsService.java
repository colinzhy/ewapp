package com.ewapp.gbank.configuration.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ewapp.gbank.model.User;
import com.ewapp.gbank.respository.UserRepository;

@Service
public class EwappUserDetailsService implements UserDetailsService {

  @Inject
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("login.username.notfound");
    }
    return new EwappUserDetails(user);
  }

}
