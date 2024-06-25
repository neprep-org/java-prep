package com.rca.myspringsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Data
@AllArgsConstructor
public class Laptop {
    @Id
    @GeneratedValue
    private int lapId;
    @Column(nullable = false, length = 100)
    private String brand;
    @Column(nullable = false, length = 100)
    private String sn;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Laptop() {

    }
    public Laptop(String brand,String sn, Student student) {
        super();
        this.brand = brand;
        this.sn = sn;
        this.student = student;
    }

    public int getLapId() {
        return lapId;
    }

    public void setLapId(int lapId) {
        this.lapId = lapId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}

