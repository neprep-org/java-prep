package com.rca.myspringsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String roles;
    @OneToMany(mappedBy = "created", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> student;
    public String getName() {
        return name;
    }
    public String getPassword() {	return password;}
    public String getRoles() {return roles;}
    public void setPassword(String encode) {
        password=encode;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
}
