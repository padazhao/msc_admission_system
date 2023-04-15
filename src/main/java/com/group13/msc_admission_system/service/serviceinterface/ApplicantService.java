package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.model.Applicant;

public interface ApplicantService {

    void register(Applicant applicant) throws Exception;

    Applicant findByEmailAndPassword(String email, String password);
}
