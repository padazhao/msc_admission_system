package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Admin;

public interface AdminService {
    void login(UserRequestDTO userRequestDTO);

    void register(UserRequestDTO userRequestDTO);
}
