package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicantServiceImplement implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    @Autowired
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

    //UPDATE==================================================================================================================
    @Transactional
    @Override
    public void updateApplicant(Long id, ApplicantRequestDTO applicantRequestDTO) {
        Applicant update = applicantRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICANT, id)));

        if (MyUtils.isNotEmptyAndNotNull(applicantRequestDTO.getUsername())) {
            update.setUsername(applicantRequestDTO.getUsername());
        }

        if (MyUtils.isNotEmptyAndNotNull(applicantRequestDTO.getEmail())) {
            update.setEmail(applicantRequestDTO.getEmail());
        }

        if (MyUtils.isNotEmptyAndNotNull(applicantRequestDTO.getGender())) {
            Gender gender = new GenderConverter().convert(applicantRequestDTO.getGender()); //CONVERTS GENDER INPUT TO ENUM TYPE GENDER
            update.setGender(gender);
        }

        if (applicantRequestDTO.getPhoneNumber() > 0) {
            update.setPhoneNumber(applicantRequestDTO.getPhoneNumber());
        }

        applicantRepository.save(update);

        System.out.println( Message.updated); // TODO: 4/30/2022 Use logs

    }
}
