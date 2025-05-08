package com.doctor.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = { CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonBackReference("appointment")
	private Appointment appointment;
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = { CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonBackReference("feedback")
	private Feedback feedback;
	
	
	
}
