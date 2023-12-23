package com.generateToken.generateToken.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generateToken.generateToken.entities.MedicinePrescription;
import com.generateToken.generateToken.entities.Prescription;
import com.generateToken.generateToken.repositories.MedicinePrescriptionRepository;
import com.generateToken.generateToken.repositories.PrescriptionRepository;
import com.generateToken.generateToken.services.MedicinePrescriptionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicinePrescriptionServiceImpl implements MedicinePrescriptionService {

    @Autowired
    private MedicinePrescriptionRepository medicinePrescriptionRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public MedicinePrescription createMedicinePrescription(Long prescriptionId,MedicinePrescription medicinePrescription) {
          medicinePrescription.setMedicineId(prescriptionId);
          return medicinePrescriptionRepository.save(medicinePrescription);
    }

    @Override
    public List<MedicinePrescription> getMedicinePrescriptionsByPrescriptionId(Long prescriptionId) {
        List<MedicinePrescription> medi = medicinePrescriptionRepository.findAll();
        List<MedicinePrescription> d = new ArrayList<>();
        for(MedicinePrescription m : medi){
            if(m.getPrescription().getPrescriptionId().equals(prescriptionId)){
                d.add(m);
            }
        }
         Prescription prescription = prescriptionRepository.findByPrescriptionId(prescriptionId);
        // prescription.setMedicinesPrescribed(d);
        return d;
    }

    @Override
    public List<MedicinePrescription> getAllMedicinePrescriptions() {
        return  medicinePrescriptionRepository.findAll();
    }

    @Override
    public String deleteMedicinePrescription(Long medicinePrescriptionId) {
        MedicinePrescription medicinePrescription = medicinePrescriptionRepository.findById(medicinePrescriptionId)
        .orElseThrow(() -> new EntityNotFoundException("MedicinePrescription not found with id: " + medicinePrescriptionId));
        medicinePrescriptionRepository.delete(medicinePrescription);
        return "MedicinePrescription with id " + medicinePrescriptionId + " deleted successfully";
    }

}
