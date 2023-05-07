package com.group13.msc_admission_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * This is a Data Transfer Object Class.
 * It is designed to hold all the inputs from the client and store it in a single object "Transfer Object"
 * Helps hold login credentials and helps validate them
 */
public class LoginCredentials {


    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must conform to email format")
    private final String email;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 3, message = "Password must contain at least 3 characters")
    private final String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password= password;
    }


}
