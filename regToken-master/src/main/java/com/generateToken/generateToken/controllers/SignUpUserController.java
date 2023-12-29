package com.generateToken.generateToken.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.dto.SignupRequest;
import com.generateToken.generateToken.entities.Doctor;
import com.generateToken.generateToken.services.DoctorService;


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
        System.out.println(HttpStatus.OK);
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


    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }


    @GetMapping("/amount")
    public ResponseEntity<?> getAmount(@RequestParam Long docId,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){

        Double amt = doctorService.findAmt(docId,startDate,endDate);
        return ResponseEntity.ok(amt);
    }

    @PutMapping("updateDoctor/{id}")
    public Doctor putMethodName(@PathVariable Long id, @RequestBody Doctor Doctor) {
        return doctorService.updateDoctor(id, Doctor);
    }

    @DeleteMapping("deleteDoctor/{id}")
    public String deleteMethodName(@PathVariable Long id) {
        return doctorService.deleteDoctor(id);
    }

    
    @GetMapping("/payment")
    public RedirectView redirectToYoutube() {
        String youtubeUrl = "http://localhost:9090/";
        return new RedirectView(youtubeUrl);
    }

}
