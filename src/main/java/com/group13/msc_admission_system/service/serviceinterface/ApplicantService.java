package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;

import java.util.List;
import java.util.Map;

public interface ApplicantService {
    void register(UserRequestDTO userRequestDTO);
    void login(LoginCredentials loginCredentials);
    Applicant getApplicantInfo(Long applicantId);
    List<Applicant> getAllApplicantInfo();
    void updateApplicant(Long id, UserRequestDTO userRequestDTO);



}
