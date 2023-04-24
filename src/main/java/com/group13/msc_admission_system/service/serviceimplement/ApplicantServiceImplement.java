package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceAlreadyExistException;
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
    public void register(UserRequestDTO userRequestDTO) {

        if(applicantRepository.findByEmail(userRequestDTO.getEmail())==null)
            throw new MyResourceAlreadyExistException(Message.resourceAlreadyExist(ResourceType.EMAIL));

        Applicant applicant = new Applicant(userRequestDTO);

        applicantRepository.save(applicant);
    }


    @Override
    public Applicant findByEmailAndPassword(String email, String password) {
        return applicantRepository.findByEmailAndPassword(email,password);
    }

    //UPDATE==================================================================================================================
    @Transactional
    @Override
    public void updateApplicant(Long id, UserRequestDTO userRequestDTO) {
        Applicant update = applicantRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICANT, id)));

        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getUsername())) {
            update.setUsername(userRequestDTO.getUsername());
        }

        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getEmail())) {
            update.setEmail(userRequestDTO.getEmail());
        }

        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getGender())) {
            Gender gender = new GenderConverter().convert(userRequestDTO.getGender()); //CONVERTS GENDER INPUT TO ENUM TYPE GENDER
            update.setGender(gender);
        }

        if (userRequestDTO.getPhoneNumber() > 0) {
            update.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }

        applicantRepository.save(update);

        System.out.println( Message.updated); // TODO: 4/30/2022 Use logs

    }
}
