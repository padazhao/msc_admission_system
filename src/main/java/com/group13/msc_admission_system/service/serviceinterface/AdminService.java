package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    void login(LoginCredentials loginCredentials);

    void register(UserRequestDTO userRequestDTO);

    Admin getAdminInfo(Long applicantId);
    Admin getAdminInfoByEmail(String email);     /* 新加入通过email获取个人信息 */
    List<Admin> getAllAdminInfo();
}
