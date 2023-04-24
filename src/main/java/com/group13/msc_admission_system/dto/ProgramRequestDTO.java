package com.group13.msc_admission_system.dto;


public class ProgramRequestDTO {

    private String name;

    private String description;

    private long programDuration;

    private long adminId;

    public String getName() {
        return name;
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
