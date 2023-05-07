package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;

import java.util.List;

public interface ApplicantService {
    Applicant register(UserRequestDTO userRequestDTO);
    void login(LoginCredentials loginCredentials);

    //void setApplicationForm(Long applicantId, ApplicationForm applicationForm);
    Applicant getApplicantInfo(Long applicantId);
    Applicant getApplicantInfoByEmail(String email);
    List<Applicant> getAllApplicantInfo();
    Applicant updateApplicant(Long id, UserRequestDTO userRequestDTO);



}
