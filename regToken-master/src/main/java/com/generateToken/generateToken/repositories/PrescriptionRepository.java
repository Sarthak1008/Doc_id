package com.generateToken.generateToken.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generateToken.generateToken.entities.Prescription;


@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
    Prescription findByPrescriptionId(Long prescriptionId);
}
