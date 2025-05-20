package com.doctor.SecurityConfig;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.doctor.service.UserServiceimpl;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserServiceimpl service) {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder());
		provider.setUserDetailsService(service);

		return provider;

	}

	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/signup/*").permitAll()
				.requestMatchers("/admin/*/*").hasAuthority("ROLE_ADMIN")
				.requestMatchers("/doctor/*/*").hasAuthority("ROLE_DOCTOR")
				.requestMatchers("/patient/*/*").hasAuthority("ROLE_PATIENT")
				.anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
				.logout(logout -> logout.logoutUrl("/logout"));
		return http.build();

	}
}
