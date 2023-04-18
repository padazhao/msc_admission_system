package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImplement implements ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public ApplicantServiceImplement(ApplicantRepository applicantRepository) {
        super();
        this.applicantRepository = applicantRepository;
    }

    @Override
    public void register(ApplicantRequestDTO applicantRequestDTO) throws Exception{
        if(applicantRepository.findByEmail(applicantRequestDTO.getEmail()).isPresent()){
            throw new Exception("Email is already exists");
        }
        Applicant applicant = new Applicant(applicantRequestDTO);
        applicantRepository.save(applicant);

    }

    @Override
    public Applicant findByEmailAndPassword(String email, String password) {
        return applicantRepository.findByEmailAndPassword(email,password);
    }
}
