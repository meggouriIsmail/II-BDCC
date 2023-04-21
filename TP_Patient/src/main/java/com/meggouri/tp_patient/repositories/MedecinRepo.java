package com.meggouri.tp_patient.repositories;

import com.meggouri.tp_patient.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepo extends JpaRepository<Medecin, Long> {
    Medecin findByNom(String nom);
}
