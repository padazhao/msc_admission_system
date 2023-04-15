package com.group13.msc_admission_system.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ApplicantRequestDTO {

    //USED TO ACCEPT DATA FOR CREATION
    @NotEmpty(message ="Username cannot be empty")
    @Size(min=3, message = "Username should contain at least 3 characters")
    private String username;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must conform to email format")
    private String email;
    @NotEmpty(message = "Gender cannot be empty")
    private static String gender;
    @NotNull(message = "User must have a phone number")
    private int phoneNumber;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
    @NotEmpty(message = "Date of birth cannot be empty")
    private static String dateOfBirth;


    //GETTERS AND SETTERS=====================================================================
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
