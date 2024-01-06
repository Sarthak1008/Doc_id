package com.generateToken.generateToken.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.entities.ApiResponse;
//import org.springframework.security.core.userdetails.User;
import com.generateToken.generateToken.entities.Doctor;
import com.generateToken.generateToken.repositories.DoctorRepository;
import com.generateToken.generateToken.services.DoctorService;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

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

    public DoctorDTO getDoctor(String email) {

        Doctor createdDoctor = doctorRepository.findFirstByEmail(email);
        createdDoctor = doctorRepository.save(createdDoctor);

        DoctorDTO doctorDTO = new DoctorDTO();


            doctorDTO.setName(createdDoctor.getName());
            doctorDTO.setSpecialization(createdDoctor.getSpecialization());
            doctorDTO.setDegree(createdDoctor.getDegree());
            doctorDTO.setExperience(createdDoctor.getExperience());
            doctorDTO.setResearch_journal(createdDoctor.getResearch_journal());
            doctorDTO.setCitations(createdDoctor.getCitations());
            doctorDTO.setContact(createdDoctor.getContact());
            doctorDTO.setEmail(createdDoctor.getEmail());
           // doctorDTO.setPassword(createdDoctor.getPassword());
            doctorDTO.setClinics(createdDoctor.getClinics());
            return doctorDTO;

    }

    @Override
    public Double findAmt(Long docId, Date startDate, Date endDate) {
        System.out.println(doctorRepository.findByTotalAmount(docId,startDate,endDate));
        return doctorRepository.findByTotalAmount(docId,startDate,endDate);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    @Transactional
    public Doctor updateDoctor(Long doctorId,Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setDegree(updatedDoctor.getDegree());
        existingDoctor.setCitations(updatedDoctor.getCitations());
        existingDoctor.setContact(updatedDoctor.getContact());
        existingDoctor.setExperience(updatedDoctor.getExperience());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setResearch_journal(updatedDoctor.getResearch_journal());
        doctorRepository.save(existingDoctor);
        return existingDoctor;
    }

    @Override
    public String deleteDoctor(long doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        if(doctor.isPresent()){
            doctorRepository.deleteById(doctorId);
        }else{
            throw new NotFoundException("Doctor not found");
        }
        String s = "Deleted doctor " + doctorId;
        return s;
    }

    @Override
    public ApiResponse forgotPassword(String email, String newPassword, String confirmPassword) {
        Doctor doctor = doctorRepository.findFirstByEmail(email);

        if (newPassword.equals(confirmPassword)) {
            if (newPassword.length() > 7 && newPassword.length() < 20) {
                if (!doctor.getPassword().equals(newPassword)) {
                    // Check if the email is present in the password
                    if (newPassword.contains(email)) {
                        return createApiResponse(HttpStatus.NOT_IMPLEMENTED, "The password should not contain the email address pattern");
                    }

                    // Password strength checks
                    if (!newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>/?].*")) {
                        return createApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "The password should contain at least one upper case letter, one lower case letter, one special character, and one digit");
                    }

                    doctor.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                    return createApiResponse(HttpStatus.OK, "Your password was successfully changed");
                } else {
                    return createApiResponse(HttpStatus.METHOD_NOT_ALLOWED, "The old password and new password should not match");
                }
            } else {
                return createApiResponse(HttpStatus.LENGTH_REQUIRED, "Both the password and confirm password length should be between 8 and 20 characters");
            }
        } else {
            return createApiResponse(HttpStatus.BAD_REQUEST, "Both the password and confirm password must match");
        }
    }


     private ApiResponse createApiResponse(HttpStatus status, String message) {
        ApiResponse response = new ApiResponse();
        response.setStatusCode(status.value());
        response.setMessage(message);
        return response;
    }
}
