package com.generateToken.generateToken.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generateToken.generateToken.entities.MedicinePrescription;
import com.generateToken.generateToken.services.MedicinePrescriptionService;

@RestController
@RequestMapping("/medicine-prescriptions")
public class MedicinePrescriptionController {

    @Autowired
    private MedicinePrescriptionService medicinePrescriptionService;

    @PostMapping("/addMedicine/{prescriptionId}")
    public ResponseEntity<MedicinePrescription> createMedicinePrescription(
            @PathVariable Long prescriptionId,
            @RequestBody MedicinePrescription medicinePrescription) {
        MedicinePrescription createdMedicinePrescription = medicinePrescriptionService.createMedicinePrescription(prescriptionId, medicinePrescription);
        return new ResponseEntity<>(createdMedicinePrescription, HttpStatus.CREATED);
    }

    @GetMapping("/getByPrescriptionId/{prescriptionId}")
    public ResponseEntity<List<MedicinePrescription>> getMedicinePrescriptionsByPrescriptionId(@PathVariable Long prescriptionId) {
        List<MedicinePrescription> medicinePrescriptions = medicinePrescriptionService.getMedicinePrescriptionsByPrescriptionId(prescriptionId);
        return new ResponseEntity<>(medicinePrescriptions, HttpStatus.OK);
    }

    @GetMapping("getAllMedicinePrescriptions")
    public ResponseEntity<List<MedicinePrescription>> getAllMedicinePrescriptions() {
        List<MedicinePrescription> medicinePrescriptions = medicinePrescriptionService.getAllMedicinePrescriptions();
        return new ResponseEntity<>(medicinePrescriptions, HttpStatus.OK);
    }

    @DeleteMapping("/{medicinePrescriptionId}")
    public ResponseEntity<String> deleteMedicinePrescription(@PathVariable Long medicinePrescriptionId) {
        String resultMessage = medicinePrescriptionService.deleteMedicinePrescription(medicinePrescriptionId);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
}