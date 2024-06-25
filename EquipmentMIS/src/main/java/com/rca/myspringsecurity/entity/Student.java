package com.rca.myspringsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserData created;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Laptop> laptops;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public UserData getCreated() {
        return created;
    }

    public void setCreated(UserData created) {
        this.created = created;
    }
}
