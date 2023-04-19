package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.model.SchoolAdmin;
import com.group13.msc_admission_system.repository.SchoolAdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.SchoolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolAdminServiceImplement implements SchoolAdminService {
    @Autowired
    private SchoolAdminRepository schoolAdminRepository;
    @Override
    public SchoolAdmin findSchoolAdminBySusernameAndpassword(String username, String password) {
        return schoolAdminRepository.findBySchoolAdminUsernameAndSchoolAdminPassword(username, password);
    }
}
