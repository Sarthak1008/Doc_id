package com.generateToken.generateToken.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generateToken.generateToken.Gender.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_contact_number", referencedColumnName = "contactNumber")
    private Appointment appointment;

    @Column(name = "currDate")
    private LocalDate currDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<MedicinePrescription> medicinesPrescribed = new ArrayList<>();

    @Column(name = "start_date_of_medication")
    private LocalDate startDateOfMedication;

    @Column(name = "end_date_of_medication")
    private LocalDate endDateOfMedication;

    // Transient fields that are not persisted in the database
    @Transient
    private int patientAge;

    @Transient
    private String contactOfClinic;

    @Transient
    private String speciality;

    @Transient
    private String location;

    // Constructor
    public Prescription(Appointment appointment, Doctor doctor) {
        this.appointment = appointment;
        this.currDate = LocalDate.now();
        this.doctor = doctor;
        this.gender = (appointment != null) ? appointment.getGender() : null;
        this.patientAge = (appointment != null) ? appointment.getAge() : 0;
        this.contactOfClinic = (doctor != null) ? doctor.getContact() : null;
        this.speciality = (doctor != null) ? doctor.getSpecialization() : null;
        this.location = (appointment != null && appointment.getClinic() != null) ?
                appointment.getClinic().getLocation() : null;
    }

    // ... other methods ...

}
