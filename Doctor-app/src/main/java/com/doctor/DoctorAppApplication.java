package com.doctor;

import com.doctor.entity.Doctor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
@EnableMethodSecurity
public class DoctorAppApplication {

    

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppApplication.class, args);
	}



}
