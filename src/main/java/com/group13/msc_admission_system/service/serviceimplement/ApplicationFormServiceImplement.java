package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.exception.MyOutOfBoundException;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.model.ApplicationForm;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.repository.ApplicantRepository;
import com.group13.msc_admission_system.repository.ApplicationFormRepository;
import com.group13.msc_admission_system.repository.ProgramRepository;
import com.group13.msc_admission_system.repository.AdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import com.group13.msc_admission_system.service.serviceinterface.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ApplicationFormServiceImplement implements ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;
    private final ProgramRepository programRepository;
    private final AdminRepository adminRepository;
    private final ApplicantRepository applicantRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailService mailService;


    //CONSTRUCTOR========================================================================================================
    @Autowired
    public ApplicationFormServiceImplement(ApplicationFormRepository applicationFormRepository,
                                           ProgramRepository programRepository,
                                           AdminRepository adminRepository,
                                           ApplicantRepository applicantRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.programRepository = programRepository;
        this.adminRepository = adminRepository;
        this.applicantRepository = applicantRepository;
    }

    //CREATE APPLICATION FORM ==========================================================================================
    @Override
    public ApplicationForm createApplication(ApplicationFormRequestDTO applicationFormRequestDTO) {
        Long applicantId = applicationFormRequestDTO.getApplicantId();
        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT, applicantId)));  //THROW EXCEPTION IF APPLICANT ID IS NOT FOUND

        Set<Program> program = validateProgramInput(applicationFormRequestDTO);

        ApplicationForm applicationForm = new ApplicationForm(applicant,program);
        
        applicationFormRepository.save(applicationForm);
        applicant.setApplicantForm(applicationForm);
        return applicationForm;
    }

    public ApplicationForm getApplicantInfo(Long applicationFormId){
        return applicationFormRepository.findByApplicationFormId(applicationFormId);
    }

    //UPDATE============================================================================================================
    @Transactional
    @Override
    public void programUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO) {
        ApplicationForm update = applicationFormRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICATION_FORM, id)));    //THROW EXCEPTION IF APPLICANT FORM ID IS NOT FOUND
        
        //Update program ID 
        if (applicationFormRequestDTO.getProgramIds().length != 0) {
            update.getProgram().clear();
            Set<Program> program = validateProgramInput(applicationFormRequestDTO);
            update.setProgram(program);
        }
        applicationFormRepository.save(update);
        System.out.println( MyMessage.updated); // TODO: Use logs
    }

    @Transactional
    @Override
    public void statusUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO){

        if(!adminRepository.existsById(applicationFormRequestDTO.getAdminId())){
            throw new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.ADMIN, applicationFormRequestDTO.getAdminId())); //THROW EXCEPTION IF APPLICANT FORM ID IS NOT FOUND
        }

        ApplicationForm update = applicationFormRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException( MyMessage.resourceNotFound(ResourceType.APPLICATION_FORM, id))   );

        //Update status
        if (MyUtils.isNotEmptyAndNotNull(applicationFormRequestDTO.getStatus())) {
            Status status = new StatusConverter().convert(applicationFormRequestDTO.getStatus());
            update.setStatus(status);
        }
        applicationFormRepository.save(update);
    }

    public void statusUpdateSendEmail(Long applicantId, String status) {
        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.APPLICANT, applicantId)));  //THROW

        String from = "yangnochicken@163.com";
        String to = applicant.getEmail();

        String subject = "Application State Changed";
        String content = "Dear Applicant, your state is changed to "+ status;
        mailService.sendSimpleMail(from, to, subject, content);
        System.out.println( MyMessage.updated +":"+ status);
    }

    /*
    * THIS IS A HELPER METHOD WHOSE ROLE IS TO VALIDATE THE INPUT FROM THE ApplicationFormRequestDTO
    * It does this by first checking if the list passed in the DTO is within the limit of the maxNumberOfSelectedProgramme
    * If not it throws an MyOutOfBoundException
    * Then for each id in the list,
    * It checks if the program exists in the database
    * If the program does not exist it throws an MyResourceNotFoundException an exits
    * If the program exist in the database, it adds it to the holder program set holder "Program"
    * Once everything is done it returns the set
    */
    private Set<Program> validateProgramInput(ApplicationFormRequestDTO applicationFormRequestDTO) {

        int maxNumberOfSelectedProgramme =  ApplicationFormRequestDTO.getMaxNumberOfSelectedProgramme();
        if(applicationFormRequestDTO.getProgramIds().length>maxNumberOfSelectedProgramme){
            throw new MyOutOfBoundException(MyMessage.invalidInput + ": Only" + maxNumberOfSelectedProgramme + " can be chosen");
        }

        Set<Program> program = new HashSet<>();
        for (Long programId: applicationFormRequestDTO.getProgramIds()) {
            program.add(
                    programRepository.findById(programId).orElseThrow(
                            ()-> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.PROGRAM, programId)))
            );
        }
        return program;
    }
}
