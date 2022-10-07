package com.pandemica.app.pandemicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pandemica.app.pandemicapp.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	
	@Query(value="select * from pandemicdb.patient where patient.name = ?1",nativeQuery = true)
	List<Patient> getVaccinatedPatient(String name);

}