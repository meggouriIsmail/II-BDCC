package com.meggouri.tp_patient.repositories;

import com.meggouri.tp_patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String nom);
    Patient findPatientByNom(String name);
}
