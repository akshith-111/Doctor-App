package com.doctor.entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
		  property = "doctorId")
public class Doctor implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	private String doctorName;
	private String speciality;
	private String hospitalName;
	private String mobileNo;
	@Column(unique = true)
	private String email;
	private String password;
	private String chargedPerVisit;
	
	@OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Appointment> appointments;
	
	@OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER,cascade =CascadeType.ALL)
	private List<Feedback> feedback;
	
	@OneToOne(mappedBy = "doctor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private AvailabilityDates availabilityDates;

	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<TreatmentHistory> treatmentHistory=new ArrayList<>();
	

}
