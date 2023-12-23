package com.generateToken.generateToken.services.Impl;



import java.util.List;
import java.util.stream.Collectors;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.entities.Doctor;
import com.generateToken.generateToken.repositories.AppointmentRepository;
import com.generateToken.generateToken.repositories.ClinicRepository;
import com.generateToken.generateToken.repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

  @Override
  public PrescriptionDto bookPrescription(Long doctorId, Long clinicId,Long appointId, PrescriptionDto prescriptionDto) {
    Clinic clinic = clinicRepository.findById(clinicId)
      .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

    Doctor doctor = doctorRepository.findById(doctorId)
      .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

    Appointment appointment = appointmentRepository.findById(appointId)
      .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

    Prescription prescription = new Prescription();
    prescription.setGender(prescriptionDto.getGender());
    prescription.setCurrDate(prescriptionDto.getCurrDate());
    prescription.setClinic(clinic);
    prescription.setDoctor(doctor);

    prescription.setSpeciality(doctor.getSpecialization());
    prescription.setDegree(doctor.getDegree());
    prescription.setSpeciality(doctor.getSpecialization());
    prescription.setStartTime(clinic.getStartTime());
    prescription.setEndTime(clinic.getEndTime());
    prescription.setAge(appointment.getAge());
    prescription.setContact(appointment.getContact_number());


    prescription = prescriptionRepository.save(prescription);

    PrescriptionDto prescriptionDto1 = new PrescriptionDto();
    prescriptionDto1.setGender(prescription.getGender());
    prescriptionDto1.setCurrDate(prescription.getCurrDate());
    //prescriptionDto1.setSpeciality(prescription.getSpeciality());

    prescription = prescriptionRepository.save(prescription);
    clinic = clinicRepository.save(clinic);
    doctor = doctorRepository.save(doctor);

    return prescriptionDto1;
  }
}
