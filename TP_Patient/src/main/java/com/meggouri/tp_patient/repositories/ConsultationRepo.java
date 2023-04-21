package com.meggouri.tp_patient.repositories;

import com.meggouri.tp_patient.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepo extends JpaRepository<Consultation, Long> {
}
