package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.common.Gender;
import com.group13.msc_admission_system.common.GenderConverter;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.time.Period;


@MappedSuperclass
public abstract class User {           // https://medium.com/analytics-vidhya/jpa-hibernate-entity-inheritance-1f6aa7ea2eea
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @Transient
    private int age ;
    @Column(name = "phone_number", nullable = false)
    private int phoneNumber;

    //CONSTRUCTOR
    public User(UserRequestDTO userRequestDTO) {

        Gender gender; //CONVERTS GENDER INPUT TO ENUM TYPE GENDER
        gender = new GenderConverter().convert(UserRequestDTO.getGender());

        this.username = userRequestDTO.getUsername();
        this.email = userRequestDTO.getEmail();
        this.password = userRequestDTO.getPassword();
        this.gender = gender;
        this.dateOfBirth = LocalDate.parse(UserRequestDTO.getDateOfBirth());
        this.age = Period.between(  this.dateOfBirth,LocalDate.now()).getYears();
        this.phoneNumber = userRequestDTO.getPhoneNumber();

    }
    public User(){}

    //GETTERS AND SETTERS==============================================================================================================

    public Long getUserId() {
        return userId;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {this.gender = gender;}

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
