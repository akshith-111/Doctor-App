package com.doctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.entity.TreatmentHistory;

public interface TreatmentHistoryRepo extends JpaRepository<TreatmentHistory, Integer>{

}
