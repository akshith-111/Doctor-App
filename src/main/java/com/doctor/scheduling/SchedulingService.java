package com.doctor.scheduling;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.doctor.entity.Doctor;
import com.doctor.entity.Patient;
import com.doctor.repo.DoctorRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulingService {

	private final DoctorRepo doctorRepo;

	@Scheduled(cron = "0 0 12 1/14 * ?")
	@Transactional
	public void deletePatientsHistory() {

		List<Doctor> doctorsList = doctorRepo.findAll();

//		doctorsList.stream().forEach(doctor -> doctor.getPatients().stream().forEach(p -> p.setDoctors(null)));

		for (Doctor doctor : doctorsList) {
			List<Patient> patientList = doctor.getPatients();
			System.out.println(doctor.getDoctorId());
			for (Patient patient : patientList) {
				patient.getDoctors().remove(doctor);
			}
			patientList.clear();

		}
	}

}