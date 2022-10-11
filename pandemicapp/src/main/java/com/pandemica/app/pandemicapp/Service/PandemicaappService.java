package com.pandemica.app.pandemicapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandemica.app.pandemicapp.entity.Patient;
import com.pandemica.app.pandemicapp.repository.PatientRepository;

@Service
public class PandemicaappService {
	@Autowired
	 private PatientRepository repo;
	 
	 public List<Patient> getVaccinatedPatient(String name) {
		
		return repo.getVaccinatedPatient(name);
	}
	 
	 public List<Patient> getVaccinatedPatientList() {
			return repo.findAll();
	 }
	 
	public Patient saveBooking(Patient p) {
		return repo.save(p);
	}
	
}
