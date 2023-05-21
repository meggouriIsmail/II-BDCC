package com.meggo.tp_hospital.security.services;

import com.meggo.tp_hospital.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = accountService.loadUserByUsername(username);
        if(user==null) throw new UsernameNotFoundException(String.format("User % not found", username));
        String[] roles = user.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        UserDetails userDetails = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
        return userDetails;
    }
}
