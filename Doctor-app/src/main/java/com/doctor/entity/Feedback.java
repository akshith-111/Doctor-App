package com.doctor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {

	@Id
	private int feedbackId;
	private String rating;
	@OneToOne
	@JsonBackReference("patient")
	private Patient patient;
	@OneToOne
	@JsonBackReference("doctor")
	private Doctor doctor;
	private String feedbackComment;
}
