package com.meggo.tp_hospital.security.repos;

import com.meggo.tp_hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {
        AppUser findByUsername(String username);
}
