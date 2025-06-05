package com.doctor.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.doctor.dto.FeedbackDTO;
import com.doctor.dto.MiniDoctorDTO;
import com.doctor.dto.MiniPatientDTO;
import com.doctor.dto.SimpleAppointmentDTO;
import com.doctor.entity.Appointment;
import com.doctor.entity.Feedback;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getMapper() {
		ModelMapper mapper=new ModelMapper();
		
		Converter<Appointment, SimpleAppointmentDTO> patientConverter= context->{
			if(context.getSource()!=null) {
			Appointment source=context.getSource();
			
			SimpleAppointmentDTO destination=new SimpleAppointmentDTO();
			
			destination.setAppointmentDate(source.getAppointmentDate());
			destination.setRemark(source.getRemark());
			destination.setStatus(source.getStatus());
			
			if(source.getDoctor()!=null)
				destination.setDoctor(mapper.map(source.getDoctor(), MiniDoctorDTO.class));
			if(source.getPatient()!=null)
				destination.setPatient(mapper.map(source.getPatient(), MiniPatientDTO.class));
			
			
			
			return destination;
			}
			return null;
					
		};
	
		
		Converter<Feedback, FeedbackDTO> feedbackConverter=context->{
			if(context.getSource()!=null) {
			Feedback source=context.getSource();
			FeedbackDTO destination=new FeedbackDTO();
			
			destination.setFeedbackComment(source.getFeedbackComment());
			destination.setRating(source.getRating());
			destination.setDoctor(mapper.map(source.getDoctor(),MiniDoctorDTO.class));
			destination.setPatient(mapper.map(source.getPatient(), MiniPatientDTO.class));
			
			return destination;
			}
			return null;
		};
		
		mapper.typeMap(Appointment.class, SimpleAppointmentDTO.class).setConverter(patientConverter);
		mapper.typeMap(Feedback.class, FeedbackDTO.class).setConverter(feedbackConverter);
		return mapper;
	}
	
}
