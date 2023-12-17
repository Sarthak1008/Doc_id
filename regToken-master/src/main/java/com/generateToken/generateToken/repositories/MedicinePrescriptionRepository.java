package com.generateToken.generateToken.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.generateToken.generateToken.entities.MedicinePrescription;

public interface MedicinePrescriptionRepository extends JpaRepository<MedicinePrescription, Long> {
}