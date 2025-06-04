package com.doctor.modelmapping;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.doctor.dto.FeedbackDTO;
import com.doctor.dto.MiniDoctorDTO;
import com.doctor.dto.MiniPatientDTO;
import com.doctor.dto.SimpleAppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Feedback;

@Component
public class MappingConverter {

	@Autowired
	public ModelMapper mapper;
	
	Converter<Appointment, SimpleAppointmentDTO> patientConverter= context->{
		
		Appointment source=context.getSource();
		
		SimpleAppointmentDTO destination=context.getDestination();
		
		destination.setAppointmentDate(source.getAppointmentDate());
		destination.setRemark(source.getRemark());
		destination.setStatus(source.getStatus());
		
		if(source.getDoctor()!=null)
			destination.setDoctor(mapper.map(source.getDoctor(), MiniDoctorDTO.class));
		if(source.getPatient()!=null)
			destination.setPatient(mapper.map(source.getPatient(), MiniPatientDTO.class));
		
		
		
		return destination;
				
	};
	
	Converter<Feedback, FeedbackDTO> feedbackConverter=context->{
		
		Feedback source=context.getSource();
		FeedbackDTO destination=context.getDestination();
		
		destination.setFeedbackComment(source.getFeedbackComment());
		destination.setRating(source.getRating());
		destination.setDoctor(mapper.map(source.getDoctor(),MiniDoctorDTO.class));
		destination.setPatient(mapper.map(source.getPatient(), MiniPatientDTO.class));
		
		return destination;
	};
	
	
}
