package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Admin;

import java.util.List;

public interface AdminService {
    void login(LoginCredentials loginCredentials);
    void register(UserRequestDTO userRequestDTO);
    Admin getAdminInfo(Long applicantId);
    Admin getAdminInfoByEmail(String email);
    List<Admin> getAllAdminInfo();
}
