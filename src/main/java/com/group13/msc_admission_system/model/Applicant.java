package com.group13.msc_admission_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicant_id",nullable = false)
    private long applicant_id;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    public Applicant(long applicant_id, String email, String username, String password) {
        this.applicant_id = applicant_id;
        this.email=email;
        this.username = username;
        this.password = password;

    }

    public Applicant() {

    }


    public void setApplicant_id(Long applicant_id) {
        this.applicant_id = applicant_id;
    }


    public Long getApplicant_id() {
        return applicant_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
