package com.generateToken.generateToken.services.Impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generateToken.generateToken.dto.PrescriptionDto;
import com.generateToken.generateToken.entities.Prescription;
import com.generateToken.generateToken.repositories.PrescriptionRepository;
import com.generateToken.generateToken.services.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository; // Assuming you have a repository for Prescription entities

    @Override
    public PrescriptionDto createPrescription(Prescription prescription) {
        return convertToDTO(prescriptionRepository.save(prescription));
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new NotFoundException("Prescription not found"));
        prescription.setSpeciality(prescription.getDoctor().getSpecialization());
        prescription.setPatientAge(prescription.getAppointment().getAge());
        prescription.setLocation(prescription.getAppointment().getClinicLocation());
        return convertToDTO(prescriptionRepository.findByPrescriptionId(prescriptionId));
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDto updatePrescription(Long prescriptionId, PrescriptionDto prescriptionDto) {
        Prescription existingEntity = prescriptionRepository.findById(prescriptionId).orElse(null);
        if (existingEntity != null) {
            BeanUtils.copyProperties(prescriptionDto, existingEntity);
            existingEntity = prescriptionRepository.save(existingEntity);
            BeanUtils.copyProperties(existingEntity, prescriptionDto);
        }
        return prescriptionDto;

    }

    @Override
    public String deletePrescription(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new NotFoundException("Prescription not found"));
        String s = "Prescription deleted "+ prescriptionId;
        prescriptionRepository.delete(prescription);
        return s;
    }

    private PrescriptionDto convertToDTO(Prescription prescription) {
        if (prescription != null) {
            PrescriptionDto prescriptionDto = new PrescriptionDto();
            BeanUtils.copyProperties(prescription, prescriptionDto);
            return prescriptionDto;
        }
        return null;
    }
    
}
