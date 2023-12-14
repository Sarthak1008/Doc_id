package com.generateToken.generateToken.dto;

import java.util.ArrayList;
import java.util.List;

import com.generateToken.generateToken.entities.Clinic;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;
    private String degree;
    private int experience;
    private String research_journal;
    private String citations;
    @Column(unique = true,length=10)
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;
    private List<Clinic> clinics = new ArrayList<>();
    public void addClinic(Clinic clinic){
        this.clinics.add(clinic);
    }
}
