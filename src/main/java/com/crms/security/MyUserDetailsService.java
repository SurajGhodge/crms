package com.crms.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crms.entity.User;
import com.crms.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User u = userRepo.findByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    List<GrantedAuthority> auth = List.of(new SimpleGrantedAuthority(u.getRole()));
    return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), auth);
  }
}
