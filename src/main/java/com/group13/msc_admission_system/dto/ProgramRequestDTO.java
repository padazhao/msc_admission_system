package com.group13.msc_admission_system.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProgramRequestDTO {

    //ATTRIBUTES =======================================================================================================
    @NotEmpty(message ="Program programName cannot be empty")
    @Size(min=4, message = "Program programName should contain at least 4 characters")
    private String programName;
    @NotNull(message = "Can be an empty string but not null")
    private String description;
    @NotNull(message = "Program must have a duration")
    private long programDuration;

    private long adminId;

    //GETTERS AND SETTERS ==============================================================================================
    public String getProgramName() {
        return programName;
    }

    public String getDescription() {
        return description;
    }

    public long getProgramDuration() {
        return programDuration;
    }

    public long getAdminId() {
        return adminId;
    }
}
