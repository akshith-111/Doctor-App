package com.doctor.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "patientId")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	private String patientName;
	private String mobileNo;
	private String email;
	private String bloodGroup;
	private String age;
	private String address;
	private String password;
	
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	private Appointment appointment;
	
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
	private Feedback feedback;

	
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
	private List<TreatmentHistory> treatmentHistory=new ArrayList<>();
	
}
