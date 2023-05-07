package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceAlreadyExistException;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import com.group13.msc_admission_system.service.serviceinterface.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicantServiceImplement implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final JavaMailSender javaMailSender;
    private final MailService mailService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public ApplicantServiceImplement(ApplicantRepository applicantRepository, JavaMailSender javaMailSender, MailService mailService) {
        super();
        this.applicantRepository = applicantRepository;
        this.javaMailSender = javaMailSender;
        this.mailService = mailService;
    }

    //REGISTER==========================================================================================================
    @Override
    public Applicant register(UserRequestDTO userRequestDTO) {
        if (applicantRepository.findByEmail(userRequestDTO.getEmail()) != null)
            throw new MyResourceAlreadyExistException(MyMessage.resourceAlreadyExist(ResourceType.EMAIL));

        Applicant applicant = new Applicant(userRequestDTO);

        /* Send register email */
        String from = "yangnochicken@163.com";
        String to = userRequestDTO.getEmail();

        String subject = "Registration";
        String content = "Successfully registered!";
        mailService.sendSimpleMail(from, to, subject, content);

        applicantRepository.save(applicant);
        return applicant;
    }

    //LOGIN=============================================================================================================
    @Override
    public void login(LoginCredentials loginCredentials) {
        Applicant applicant = applicantRepository.findByEmailAndPassword(loginCredentials.getEmail(), loginCredentials.getPassword());
        if (applicant == null) {                                   //THROW EXCEPTION IF ADMIN NOT FOUND
            throw new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT));
        }
    }

//    @Override
//    public void setApplicationForm(Long applicantId,ApplicationForm applicationForm) {
//        Applicant applicant =  applicantRepository.findById(applicantId).orElseThrow(
//                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT, applicantId)));
//        applicant.setApplicantForm(applicationForm);
//    }

    //LOGIN=============================================================================================================
    @Override
    public Applicant getApplicantInfo(Long applicantId) {
        return applicantRepository.findById(applicantId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT, applicantId)));
    }


    @Override
    public Applicant getApplicantInfoByEmail(String email) {
        return applicantRepository.findByEmail(email);
    }


    @Override
    public List<Applicant> getAllApplicantInfo() {
        return applicantRepository.findAll();
    }

    //UPDATE============================================================================================================
    @Transactional
    @Override
    public Applicant updateApplicant(Long id, UserRequestDTO userRequestDTO) {
        Applicant update = applicantRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT, id)));


        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getUsername())) {
            update.setUsername(userRequestDTO.getUsername());
        }

        if (MyUtils.isNotEmptyAndNotNull(userRequestDTO.getEmail())) {
            update.setEmail(userRequestDTO.getEmail());
        }

        if (MyUtils.isNotEmptyAndNotNull(UserRequestDTO.getGender())) {
            Gender gender = new GenderConverter().convert(UserRequestDTO.getGender()); //CONVERTS GENDER INPUT TO ENUM TYPE GENDER
            update.setGender(gender);
        }

        if (userRequestDTO.getPhoneNumber() > 0) {
            update.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }

        applicantRepository.save(update);

        System.out.println(MyMessage.updated);

        return update;
    }


}
