package com.meggo.tp_hospital.security.repos;

import com.meggo.tp_hospital.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
