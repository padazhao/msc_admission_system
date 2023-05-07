package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.common.Status;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.model.ApplicationForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ApplicationFormService {

    ApplicationForm createApplication(ApplicationFormRequestDTO applicationFormRequestDTO);
    void programUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO);
    @Transactional
    void statusUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO);
    ApplicationForm getApplicantInfo(Long applicantId);
}
