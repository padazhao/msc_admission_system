package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.common.Status;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ApplicationFormService {
    void programUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO);

    @Transactional
    void statusUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO);
}
