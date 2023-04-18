package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ApplicationFormService {
    void updateApplicationForm(Long id, ApplicationFormRequestDTO applicationFormRequestDTO);
}
