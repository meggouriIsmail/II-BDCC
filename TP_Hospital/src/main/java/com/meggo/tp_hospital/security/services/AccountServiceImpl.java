package com.meggo.tp_hospital.security.services;

import com.meggo.tp_hospital.security.entities.Role;
import com.meggo.tp_hospital.security.entities.AppUser;
import com.meggo.tp_hospital.security.repos.RoleRepository;
import com.meggo.tp_hospital.security.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser user= userRepository.findByUsername(username);
        if (user!=null) throw new RuntimeException("This user already exist");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords isn't matching");
        user=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(String role) {
        Role appRole=roleRepository.findById(role).orElse(null);
        if (appRole!=null) throw new RuntimeException("This role is already exist");
        appRole=Role.builder()
                .role(role)
                .build();
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user=userRepository.findByUsername(username);
        if (user==null) throw new RuntimeException("This user not existed");
        Optional<Role> appRole=roleRepository.findById(role);
        if (appRole.isEmpty()) throw new RuntimeException("This role not existed");
        user.getRoles().add(appRole.get());
        userRepository.save(user);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser user=userRepository.findByUsername(username);
        if (user==null) throw new RuntimeException("This user not existed");
        Optional<Role> appRole=roleRepository.findById(role);
        if (appRole.isEmpty()) throw new RuntimeException("This role not existed");
        user.getRoles().remove(appRole.get());
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}