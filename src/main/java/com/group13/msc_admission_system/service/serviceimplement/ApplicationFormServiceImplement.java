package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.*;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.ApplicationForm;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.repository.ApplicationFormRepository;
import com.group13.msc_admission_system.repository.ProgramRepository;
import com.group13.msc_admission_system.repository.AdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ApplicationFormServiceImplement implements ApplicationFormService {

    private final ApplicationFormRepository applicationFormRepository;
    private final ProgramRepository programRepository;
    private final AdminRepository AdminRepository;

    @Autowired
    public ApplicationFormServiceImplement(ApplicationFormRepository applicationFormRepository,
                                           ProgramRepository programRepository,
                                           AdminRepository AdminRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.programRepository = programRepository;
        this.AdminRepository = AdminRepository;
    }

    //UPDATE============================================================================================================
    @Transactional
    @Override
    public void programUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO) {

        ApplicationForm update = applicationFormRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.APPLICATION_FORM, id)));
        
        //Update program ID 
        if (applicationFormRequestDTO.getProgramId().length != 0) {
            update.getProgram().clear();
            Set<Program> program = null;
            for (Long programId:applicationFormRequestDTO.getProgramId()) {
                program.add(
                        programRepository.findById(programId).orElseThrow(
                                ()-> new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.PROGRAM, programId)))
                );
            }
            update.setProgram(program);
        }

        applicationFormRepository.save(update);

        System.out.println( Message.updated); // TODO: 4/30/2022 Use logs
    }

    @Transactional
    @Override
    public void statusUpdate(Long id, ApplicationFormRequestDTO applicationFormRequestDTO){

        if(!AdminRepository.existsById(applicationFormRequestDTO.getAdminId())){
            throw new MyResourceNotFoundException(Message.resourceNotFound(ResourceType.ADMIN, applicationFormRequestDTO.getAdminId()));
        }

        ApplicationForm update = applicationFormRepository.findById(id).orElseThrow(
                () -> new MyResourceNotFoundException( Message.resourceNotFound(ResourceType.APPLICATION_FORM, id))   );

        //Update status
        if (MyUtils.isNotEmptyAndNotNull(applicationFormRequestDTO.getStatus())) {
            Status status = new StatusConverter().convert(applicationFormRequestDTO.getStatus());
            update.setStatus(status);
        }

        applicationFormRepository.save(update);

        System.out.println( Message.updated);
    }
}
