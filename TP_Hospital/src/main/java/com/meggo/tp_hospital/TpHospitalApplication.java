package com.meggo.tp_hospital;

import com.meggo.tp_hospital.entities.Patient;
import com.meggo.tp_hospital.repositories.PatienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TpHospitalApplication {


//    @Autowired
//    private PatienRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(TpHospitalApplication.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {

        return args -> {
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user1").password(passwordEncoder().encode("12345")).roles("USER").build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("user2").password(passwordEncoder().encode("12345")).roles("USER").build()
            );
            jdbcUserDetailsManager.createUser(
                    User.withUsername("admin").password(passwordEncoder().encode("12345")).roles("USER","ADMIN").build()
            );
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
