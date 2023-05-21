package com.meggo.tp_hospital.security.services;

import com.meggo.tp_hospital.security.entities.Role;
import com.meggo.tp_hospital.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    Role addNewRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
