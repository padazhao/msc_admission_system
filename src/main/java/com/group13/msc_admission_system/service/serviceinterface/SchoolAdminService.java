package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.model.SchoolAdmin;

public interface SchoolAdminService {
    SchoolAdmin findSchoolAdminBySusernameAndpassword(String username, String password);
}
