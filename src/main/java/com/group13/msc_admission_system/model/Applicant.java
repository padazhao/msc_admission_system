package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.dto.UserRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name="applicant")
public class Applicant extends User{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="applicant_id",nullable = false)
//    private long applicantId;
//    @Column(name="username",nullable = false)
//    private String username;
//    @Column(name="email", nullable = false)
//    private String email;
//    @Column(name = "password", nullable = false)
//    private String password;
//    @Column(name = "gender", nullable = false)
//    private Gender gender;
//    @Past(message = "Date of birth must be in the past")
//    private LocalDate dateOfBirth;
//    @Transient
//    private int age ;
//    @Column(name = "phone_number", nullable = false)
//    private int phoneNumber;

    //TODO Use @CreationTimestamp to record timestamp in database and @UpdateTimestamp


    //CONSTRUCTOR======================================================================================================================
    public Applicant(UserRequestDTO userRequestDTO) {super(userRequestDTO);}

    public Applicant(){}


    //GETTERS AND SETTERS==============================================================================================================

//    public Long getUserId() {
//        return applicantId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {this.gender = gender;}
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public int getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(int phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

}
