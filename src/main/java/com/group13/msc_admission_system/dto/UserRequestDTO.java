package com.group13.msc_admission_system.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * This is a Data Transfer Object Class.
 * It is designed to 'register' all the inputs from the client and store it in a single object "Transfer Object"
 * This object is later mapped to the 'real' Applicant Object accordingly
 * Helps seperate concerns, from client object and server object.
 * Modifications on server object does not affect client's object
 */
public class UserRequestDTO {

    //USED TO ACCEPT DATA FOR CREATION
    @NotEmpty(message ="Username cannot be empty")
    @Size(min=3, message = "Username should contain at least 3 characters")
    private String username;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must conform to email format")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 3, message = "Password must contain at least 3 characters")
    private String password;
    @NotEmpty(message = "Gender cannot be empty")
    private static String gender;
    @NotNull(message = "Age cannot be empty")
    private int age;
    @NotNull(message = "User must have a phone number")
    private int phoneNumber;
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
