package com.generateToken.generateToken.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generateToken.generateToken.dto.AppointmentDTOs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String incharge;
    private int fees;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
    

    @OneToMany( cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

    public void addAppointmentPatient(Appointment appointment){
        this.appointmentList.add(appointment);
    }


    public List<AppointmentDTOs> getAppointmentDto(){
        List<AppointmentDTOs> appointmentDTOs = new ArrayList<>();
        for(Appointment appointment : this.appointmentList){
            appointmentDTOs.add(appointment.getAppointmentDto());
        }
        return appointmentDTOs;
    }


}
