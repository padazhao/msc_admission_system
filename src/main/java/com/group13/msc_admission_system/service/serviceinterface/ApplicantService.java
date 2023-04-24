package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;

import java.util.Map;

public interface ApplicantService {

    void register(UserRequestDTO userRequestDTO);

    void login(LoginCredentials loginCredentials);

    void updateApplicant(Long id, UserRequestDTO userRequestDTO);
}
