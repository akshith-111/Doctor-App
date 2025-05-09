package com.doctor.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
//@JsonIgnoreProperties("inspection")
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
	
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//	@JsonBackReference
	private Appointment appointment;
	
	@OneToOne(mappedBy = "patient",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Feedback feedback;
	
	
	
}
