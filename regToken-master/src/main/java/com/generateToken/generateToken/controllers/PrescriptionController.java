package com.generateToken.generateToken.controllers;

import java.util.List;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generateToken.generateToken.dto.PrescriptionDto;
import com.generateToken.generateToken.entities.Prescription;
import com.generateToken.generateToken.services.PrescriptionService;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

  @PostMapping("/book1")
  public PrescriptionDto bookAppointment(@RequestParam Long doctorId,
                                         @RequestParam Long clinicId,
                                         @RequestParam Long appoinId,
                                         @RequestBody PrescriptionDto prescriptionDto
  ) {

    return prescriptionService.bookPrescription(doctorId, clinicId,appoinId, prescriptionDto);
  }

//    @PostMapping("/addPrescription/{phoneNumber}")
//    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody Prescription prescription) {
//        PrescriptionDto createdPrescription = prescriptionService.createPrescription(prescription);
//        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{prescriptionId}")
//    public ResponseEntity<PrescriptionDto> getPrescription(@PathVariable Long prescriptionId) {
//        PrescriptionDto prescription = prescriptionService.getPrescriptionById(prescriptionId);
//        if (prescription != null) {
//            return new ResponseEntity<>(prescription, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions() {
//        List<PrescriptionDto> prescriptions = prescriptionService.getAllPrescriptions();
//        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
//    }
//
//    @PutMapping("/{prescriptionId}")
//    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable Long prescriptionId,@RequestBody PrescriptionDto prescriptionDto) {
//        PrescriptionDto updatedPrescription = prescriptionService.updatePrescription(prescriptionId, prescriptionDto);
//        if (updatedPrescription != null) {
//            return new ResponseEntity<>(updatedPrescription, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{prescriptionId}")
//    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId) {
//        prescriptionService.deletePrescription(prescriptionId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
