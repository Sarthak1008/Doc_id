package com.generateToken.generateToken.services.Impl;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.entities.Doctor;
import com.generateToken.generateToken.repositories.AppointmentRepository;
import com.generateToken.generateToken.repositories.ClinicRepository;
import com.generateToken.generateToken.repositories.DoctorRepository;
import com.generateToken.generateToken.services.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Appointment getByAadhar(String aadharCard) {
      // return appointmentRepository.findByAadharCard(aadharCard);
        return null;
    }

    @Override
    public AppointmentDTOs bookAppointment(Long doctorId,Long clinicId, AppointmentDTOs appointmentDto) {

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));


        Appointment patientAppointment = new Appointment();

        patientAppointment.setName(appointmentDto.getName());
        patientAppointment.setContactNumber(appointmentDto.getContactNumber());
        patientAppointment.setAadharNumber(appointmentDto.getAadharNumber());
        patientAppointment.setAge(appointmentDto.getAge());
        patientAppointment.setGender(appointmentDto.getGender());
        patientAppointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        patientAppointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        patientAppointment.setClinicLocation(appointmentDto.getClinicLocation());
        patientAppointment.setClinic(clinic);
        patientAppointment.setDoctor(doctor);


        patientAppointment = appointmentRepository.save(patientAppointment);


        AppointmentDTOs appointmentDto1 =  new AppointmentDTOs();
        appointmentDto1.setName(patientAppointment.getName());
        appointmentDto1.setContactNumber(patientAppointment.getContactNumber());
        appointmentDto1.setAadharNumber(patientAppointment.getAadharNumber());
        appointmentDto1.setAge(patientAppointment.getAge());
        appointmentDto1.setGender(patientAppointment.getGender());
        appointmentDto1.setAppointmentDate(patientAppointment.getAppointmentDate());
        appointmentDto1.setAppointmentTime(patientAppointment.getAppointmentTime());
        appointmentDto1.setClinicLocation(patientAppointment.getClinicLocation());


        clinic.addAppointmentPatient(patientAppointment);
        doctor.addAppointmentPatient1(patientAppointment);

        patientAppointment = appointmentRepository.save(patientAppointment);
        clinic = clinicRepository.save(clinic);
        doctor = doctorRepository.save(doctor);

        return appointmentDto1;
    }

}
