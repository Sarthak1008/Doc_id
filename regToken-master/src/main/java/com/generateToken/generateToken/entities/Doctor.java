package com.generateToken.generateToken.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialization;
    private String degree;
    private int experience;
    private String research_journal;
    private String citations;
//    @Column(unique = true,length=10)
//    private String contact;

    @Column(unique = true, length = 10)
    @Size(min = 10, max = 10, message = "Contact length must be exactly 10 characters")
    private String contact;
    @Column(unique = true)
    private String email;
    private String password;





    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Clinic> clinics = new ArrayList<>();

    public void addClinic(Clinic clinic) {
        this.clinics.add(clinic);
    }

    public void removeClinic(Clinic clinic) {
        this.clinics.remove(clinic);
        clinic.setDoctor(null);
    }

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointmentPatientList = new ArrayList<>();

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Prescription> prescriptionList = new ArrayList<>();


    public void addAppointmentPatient1(Appointment appointment){
        this.appointmentPatientList.add(appointment);
    }

}
