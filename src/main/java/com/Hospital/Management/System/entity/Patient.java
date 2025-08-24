package com.Hospital.Management.System.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

//    declare the variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "fees")
    private String fees;

    @Column(name = "urgency")
    private String urgency;

//    create constructor with fields

    public Patient(long id, String name, int age, String bloodGroup, String prescription, String fees, String urgency) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.prescription = prescription;
        this.fees = fees;
        this.urgency = urgency;
    }

    //    create super class constructor
    public Patient() {
        super();
    }

//    create setters and getters methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
