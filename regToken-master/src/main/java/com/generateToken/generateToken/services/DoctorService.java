package com.generateToken.generateToken.services;

import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.dto.DoctorDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface DoctorService {
    DoctorDTO createUser(SignupRequest signupRequest);

    DoctorDTO getDoctor(Long docId);

    Double findAmt(Long docId,Date startDate, Date endDate);
    //

}
