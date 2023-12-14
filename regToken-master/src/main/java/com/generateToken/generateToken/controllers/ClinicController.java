package com.generateToken.generateToken.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.dto.ClinicDto;
import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.services.AppointmentService;
import com.generateToken.generateToken.services.ClinicService;

@RestController
@RequestMapping("/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add")
    public ResponseEntity<?> addClinicsToDoctor(@RequestParam Long userId, @RequestBody ClinicDto clinicDto){
        System.out.println("ANISH");
        ClinicDto clinicDto1 = clinicService.addClinic(userId,clinicDto);
        if (clinicDto1 == null){
            return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clinicDto1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable("id") Long id) {
        System.out.println("anish");
        Optional<Clinic> clinic = clinicService.getClinicById(id);
        if (clinic.isPresent()) {
            return ResponseEntity.ok(clinic.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{doctor_id}/{id}")
    public ResponseEntity<?> deleteClinic(@PathVariable Long doctor_id,@PathVariable Long id) {
        System.out.println("hello");
        String str = clinicService.deleteClinic(doctor_id, id);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateClinic(@PathVariable Long id, @RequestBody ClinicDto updatedClinicDto) {
        try {
            ClinicDto updatedClinic = clinicService.updateClinic(id, updatedClinicDto);
            return new ResponseEntity<>(updatedClinic, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getApt")
    public ResponseEntity<?> getAppointments(@RequestParam Long clinicId){
        System.out.println("anish");
        List<AppointmentDTOs> appointmentDTOsList = clinicService.getAppointments(clinicId);
        if (!appointmentDTOsList.isEmpty()) {
            return ResponseEntity.ok(appointmentDTOsList);
        } else {
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("betweenDate")
    public List<AppointmentDTOs> getAppointmentBetweenDate(
            @RequestParam Long clinicId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate endDate
            ){

        return clinicService.getAppointmentBetweenDate(clinicId,startDate,endDate);

    }

    @GetMapping("/getAll")
    public List<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }
    

    @GetMapping("/amount")
    public ResponseEntity<Double> findAmountForClinicInDateRange(
            @RequestParam Long clinicId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        Double amount = clinicService.findAmountForClinicInDateRange(clinicId, startDate, endDate);

        if (amount != null) {
            return ResponseEntity.ok(amount);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
