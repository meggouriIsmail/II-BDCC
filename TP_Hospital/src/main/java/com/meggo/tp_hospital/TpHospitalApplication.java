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
public class TpHospitalApplication implements CommandLineRunner {


//    @Autowired
//    private PatienRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(TpHospitalApplication.class, args);
    }

    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        PasswordEncoder passwordEncoder;
        return args -> {
            UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
            if (u1==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder().encode("1234")).disabled(false).roles("USER").build()
                );
            UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
            if (u2==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder().encode("1234")).disabled(true).roles("USER").build()
                );
            UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (u3==null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder().encode("1234")).disabled(false).roles("USER","ADMIN").build()
                );
        };
    }

    @Override
    public void run(String... args) throws Exception {
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
