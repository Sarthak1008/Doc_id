package com.generateToken.generateToken.controllers;

import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/home")
public class SignUpUserController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        System.out.println("hello world");
        DoctorDTO createdUser = doctorService.createUser(signupRequest);
        if (createdUser == null){
            return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<DoctorDTO> getUser(@RequestParam Long docId){

        DoctorDTO doctor =  doctorService.getDoctor(docId);

        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/amount")
    public ResponseEntity<?> getAmount(@RequestParam Long docId,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
                                      {

        Double amt = doctorService.findAmt(docId,startDate,endDate);
        return ResponseEntity.ok(amt);
    }

}
