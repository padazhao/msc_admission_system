package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;

public interface ApplicantService {

    void register(UserRequestDTO userRequestDTO);

    Applicant findByEmailAndPassword(String email, String password);

    void updateApplicant(Long id, UserRequestDTO userRequestDTO);
}
