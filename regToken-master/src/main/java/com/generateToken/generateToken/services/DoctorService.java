package com.generateToken.generateToken.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.entities.ApiResponse;
import com.generateToken.generateToken.entities.Doctor;

@Service
public interface DoctorService {
    DoctorDTO createUser(SignupRequest signupRequest);
    List<Doctor> getAllDoctors();
    DoctorDTO getDoctor(String email);
    Double findAmt(Long docId,Date startDate, Date endDate);
    Doctor updateDoctor(Long doctorId,Doctor doctor);
    String deleteDoctor(long doctorId);
    ApiResponse forgotPassword(String email,String newPassword,String confirmPassword);
    //

}
