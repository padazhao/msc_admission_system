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
    Applicant getApplicantInfoByEmail(String email);     /* 新加入通过email获取个人信息 */
    List<Applicant> getAllApplicantInfo();
    Applicant updateApplicant(Long id, UserRequestDTO userRequestDTO);



}
