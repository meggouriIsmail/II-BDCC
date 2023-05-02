package com.meggo.tp_hospital;

import com.meggo.tp_hospital.entities.Patient;
import com.meggo.tp_hospital.repositories.PatienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    @Override
    public void run(String... args) throws Exception {
//        Stream.of("Moha","Reda", "Amin").forEach(name -> {
//            patientRepository.save(Patient.builder()
//                .nom(name)
//                .score(2)
//                .dateNaissance(new Date())
//                .malade(true)
//                .build()
//            );
//        });
    }
}
