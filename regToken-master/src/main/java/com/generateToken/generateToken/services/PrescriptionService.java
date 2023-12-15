package com.generateToken.generateToken.services;


import java.util.List;

import com.generateToken.generateToken.dto.PrescriptionDto;
import com.generateToken.generateToken.entities.Prescription;

public interface PrescriptionService {

    // Create
    PrescriptionDto createPrescription(Prescription prescription);

    // Read
    PrescriptionDto getPrescriptionById(Long prescriptionId);
    List<PrescriptionDto> getAllPrescriptions();

    // Update
    PrescriptionDto updatePrescription(Long prescriptionId, PrescriptionDto prescriptionDto);

    // Delete
    String deletePrescription(Long prescriptionId);
}

