package com.pandemica.app.pandemicapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="patient")
public class Patient {
	
	public Patient(String name, String emailId, Date date, String venue, boolean vaccinated) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.date = date;
		this.venue = venue;
		this.vaccinated = vaccinated;
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public boolean isVaccinated() {
		return vaccinated;
	}
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	@Column(name = "emailId")
	private String emailId;
	@Column(name = "date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date date;
	@Column(name = "venue")
	private String venue;
	@Column(name = "vaccinated")
	private boolean vaccinated;
	
		
	
}