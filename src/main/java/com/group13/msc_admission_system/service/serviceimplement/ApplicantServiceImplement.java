package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyInvalidInputException;
import com.group13.msc_admission_system.exception.MyResourceAlreadyExistException;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ApplicantServiceImplement implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public ApplicantServiceImplement(ApplicantRepository applicantRepository) { super();
        this.applicantRepository = applicantRepository;
    }

    //REGISTER==========================================================================================================
    @Override
    public void register(UserRequestDTO userRequestDTO) {
        if(applicantRepository.findByEmail(userRequestDTO.getEmail())!=null)
            throw new MyResourceAlreadyExistException(Message.resourceAlreadyExist(ResourceType.EMAIL));

        Applicant applicant = new Applicant(userRequestDTO);
        applicantRepository.save(applicant);
    }

    //LOGIN=============================================================================================================
    @Override
    public void login(LoginCredentials loginCredentials) {
        Applicant applicant = applicantRepository.findByEmailAndPassword(loginCredentials.getEmail(), loginCredentials.getPassword());
        if(applicant==null)                                     //THROW EXCEPTION IF ADMIN NOT FOUND
            throw new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICANT));
    }

    //LOGIN=============================================================================================================
    @Override
    public Applicant getApplicantInfo(Long applicantId) {
        return applicantRepository.findById(applicantId).orElseThrow(
                () -> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICANT, applicantId)));
    }

    @Override
    public List<Applicant> getAllApplicantInfo() {
        return applicantRepository.findAll();
    }

    //UPDATE============================================================================================================
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

        System.out.println( Message.updated);

    }


}
