package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.dto.ApplicantResponseDTO;
import com.group13.msc_admission_system.model.Applicant;

public interface ApplicantService {

    void register(ApplicantRequestDTO applicantRequestDTO) throws Exception;

    Applicant findByEmailAndPassword(String email, String password);

    void updateApplicant(Long id, ApplicantRequestDTO applicantRequestDTO);
}
