package com.doctor.scheduling;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.repo.AppointmentRepo;
import com.doctor.repo.DoctorRepo;
import com.doctor.service.IAppointmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulingService {

	private final IAppointmentService appointmentService;

	private final AppointmentRepo appointmentRepo;

	@Scheduled(cron = "0 0 0 * * ?")
	@Transactional
	public void deleteAppointments() {
		
		List<Appointment> appointmentList = appointmentRepo.findAll();

		for (Appointment appointment : appointmentList) {
			
			Period period = Period.between(appointment.getAppointmentDate(), LocalDate.now());

			System.out.println(period.getDays());

			if (period.getDays() >= 7) {

				appointmentService.deleteAppointment(appointment.getAppointmentId());

			}
		}
	}

}