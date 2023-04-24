package com.group13.msc_admission_system.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AdminRequestDTO {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, message = "Username should contain at least 3 characters")
    private String adminUsername;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 3, message = "Password must contain at least 3 characters")
    private String adminPassword;

    //GETTERS AND SETTERS=========================================================================
    public String getAdminUsername() { return adminUsername; }

    public String getAdminPassword() { return adminPassword; }

}
