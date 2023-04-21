package com.meggouri.tp_patient;

import com.meggouri.tp_patient.entities.Patient;
import com.meggouri.tp_patient.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TpPatientApplication implements CommandLineRunner {

	@Autowired
	private PatientRepo patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(TpPatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		patientRepository.save(new Patient(null, "Hmadi", new SimpleDateFormat( "yyyy/MM/dd" ).parse( "1995/05/20" ), true, 3));
//		patientRepository.save(new Patient(null, "Mkwari", new SimpleDateFormat( "yyyy/MM/dd" ).parse( "1985/05/20" ), true, 1));
//		patientRepository.save(new Patient(null, "Lwafi", new SimpleDateFormat( "yyyy/MM/dd" ).parse( "2001/05/20" ), false, 0));


		//afficher tout les patients
		System.out.println("Liste des patients");
		List<Patient> patients=patientRepository.findAll();
		patients.forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("-----------------------------------------------");
		//Afficher un patient par Id
		Patient patient=patientRepository.findById(Long.valueOf(3)).get();
		System.out.println(patient);
		System.out.println("*********************");

		//cherhcer des patients
		System.out.println("Chercher des patients par mot cle");
		List<Patient> patients1= patientRepository.findByNomContains("H");
		patients1.forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("-----------------------------------------------");

		//Mettre a jour un patient
		Patient patientToUpdate = patientRepository.findById(1L).get();
		patientToUpdate.setNom("updatedName");
		patientToUpdate.setMalade(false);
		patientToUpdate.setScore(60);
		patientRepository.save(patientToUpdate);

		//supprimer un patient
		patientRepository.deleteById(2L);
	}
}
