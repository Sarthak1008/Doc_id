package com.generateToken.generateToken.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.services.AppointmentService;
//public class AppointmentController {
//}
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book1")
    public AppointmentDTOs bookAppointment(@RequestParam Long doctorId,
            @RequestParam Long clinicId,
            @RequestBody AppointmentDTOs appointmentPatient
            ) {

        return appointmentService.bookAppointment(doctorId, clinicId, appointmentPatient);
    }

    @GetMapping("/getByNumber")
    public ResponseEntity<List<Appointment>> getPatientByAadhar(@RequestParam String aadharCardNumber){

        List<Appointment> patient = appointmentService.getByAadhar(aadharCardNumber);

        if(patient!=null){
            return ResponseEntity.ok(patient);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
