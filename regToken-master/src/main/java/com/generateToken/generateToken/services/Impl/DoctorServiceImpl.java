package com.generateToken.generateToken.services.Impl;

import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.repositories.DoctorRepository;
import com.generateToken.generateToken.services.DoctorService;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import com.generateToken.generateToken.entities.Doctor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service

public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transient
    public DoctorDTO createUser(SignupRequest signupRequest) {
        Doctor doctor = new Doctor();
        doctor.setName(signupRequest.getName());
        doctor.setSpecialization(signupRequest.getSpecialization());
        doctor.setDegree(signupRequest.getDegree());
        doctor.setExperience(signupRequest.getExperience());
        doctor.setResearch_journal(signupRequest.getResearch_journal());
        doctor.setCitations(signupRequest.getCitations());
        doctor.setContact(signupRequest.getContact());
        doctor.setEmail(signupRequest.getEmail());
        doctor.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        Doctor createdDoctor = doctorRepository.save(doctor);
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(createdDoctor.getName());
        doctorDTO.setSpecialization(createdDoctor.getSpecialization());
        doctorDTO.setDegree(createdDoctor.getDegree());
        doctorDTO.setExperience(createdDoctor.getExperience());
        doctorDTO.setResearch_journal(createdDoctor.getResearch_journal());
        doctorDTO.setCitations(createdDoctor.getCitations());
        doctorDTO.setContact(createdDoctor.getContact());
        doctorDTO.setEmail(createdDoctor.getEmail());
        doctorDTO.setPassword(createdDoctor.getPassword());
       // doctorDTO.setClinics(createdDoctor.getClinics());
        return doctorDTO;




    }

//    private List<Clinic> mapAllClinics(List<ClinicDto> clinicDtoList){
//        return clinicDtoList.stream().map(data -> {
//            Clinic clinic = new Clinic();
//            clinic.setLocation(data.getLocation());
//            clinic.setIncharge(data.getIncharge());
//            return clinic;
//        }).collect(Collectors.toList());
//
//    }

    public DoctorDTO getDoctor(Long id) {
       // return userRepository.findById(id).orElse(null);
       // return userRepository.findById(id).orElse(null);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if(optionalDoctor.isPresent()){
            Doctor createdDoctor = optionalDoctor.get();
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setName(createdDoctor.getName());
            doctorDTO.setSpecialization(createdDoctor.getSpecialization());
            doctorDTO.setDegree(createdDoctor.getDegree());
            doctorDTO.setExperience(createdDoctor.getExperience());
            doctorDTO.setResearch_journal(createdDoctor.getResearch_journal());
            doctorDTO.setCitations(createdDoctor.getCitations());
            doctorDTO.setContact(createdDoctor.getContact());
            doctorDTO.setEmail(createdDoctor.getEmail());
            doctorDTO.setPassword(createdDoctor.getPassword());
            doctorDTO.setClinics(createdDoctor.getClinics());
            return doctorDTO;
        }else{
            return null;
        }
    }

    @Override
    public Double findAmt(Long docId, Date startDate, Date endDate) {
        System.out.println(doctorRepository.findByTotalAmount(docId,startDate,endDate));
        return doctorRepository.findByTotalAmount(docId,startDate,endDate);
    }
}
