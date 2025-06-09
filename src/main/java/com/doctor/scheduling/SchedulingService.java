package com.doctor.scheduling;


import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.doctor.entity.Appointment;
import com.doctor.repo.AppointmentRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulingService {

	private final AppointmentRepo appointmentRepo;

	@Scheduled(cron = "0 0 23 1/14 * ?")
	@Transactional
	public void deleteAppointments() {
		System.out.println("ENTERED");
		List<Appointment> appointmentList=appointmentRepo.findAll();
		
		for(Appointment appointment:appointmentList) {
			
			appointment.getDoctor().getAppointments().clear();
			appointment.getPatient().setAppointment(null);
			
		}
	}

}