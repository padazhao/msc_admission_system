package com.group13.msc_admission_system.dto;

import com.group13.msc_admission_system.common.Message;
import com.group13.msc_admission_system.exception.MyOutOfBoundException;

/**
 * This is a Data Transfer Object Class.
 * It is designed to 'register' all the inputs from the client and store it in a single object "Transfer Object"
 * This object is later mapped to the 'real' Applicant Object accordingly
 * Helps seperate concerns, from client object and server object.
 * Modifications on server object does not affect client's object
 */
public class ApplicationFormRequestDTO {

    private static int maxNumberOfSelectedProgramme = 2;
    private Long[] programIds;
    private String status;
    private Long adminId;
    private Long applicantId;

    public Long getApplicantId() {
        return applicantId;
    }

    public Long[] getProgramIds() {
        return programIds;
    }
    public String getStatus() { return status;}

    public static int getMaxNumberOfSelectedProgramme() {
        return maxNumberOfSelectedProgramme;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setProgramIds(Long[] programIds) {
        this.programIds = programIds;
    }

    public static void setMaxNumberOfSelectedProgramme(int maxNumberOfSelectedProgramme) {
        ApplicationFormRequestDTO.maxNumberOfSelectedProgramme = maxNumberOfSelectedProgramme;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
