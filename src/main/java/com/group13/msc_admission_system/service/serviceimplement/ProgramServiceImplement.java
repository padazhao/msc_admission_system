package com.group13.msc_admission_system.service.serviceimplement;

import com.group13.msc_admission_system.common.MyMessage;
import com.group13.msc_admission_system.common.MyUtils;
import com.group13.msc_admission_system.common.ResourceType;
import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.repository.ProgramRepository;
import com.group13.msc_admission_system.repository.AdminRepository;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImplement implements ProgramService {

    private final ProgramRepository programRepository;
    private final AdminRepository adminRepository;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public ProgramServiceImplement(ProgramRepository programRepository, AdminRepository adminRepository) {
        this.programRepository = programRepository;
        this.adminRepository = adminRepository;
    }

    //CREATE ===========================================================================================================
    @Override
    public void createProgram(ProgramRequestDTO programRequestDTO) {
        Program program = new Program(programRequestDTO);
        programRepository.save(program);
        System.out.println(ResourceType.PROGRAM + MyMessage.created);
    }

    @Override
    public Program getProgram(Long programId) {
        return programRepository.findById(programId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.PROGRAM, programId)));
    }

    @Override
    public List<Program> getAllProgram() {
        return programRepository.findAll();
    }

    //UPDATE============================================================================================================
    @Override
    public void programUpdate(Long id, ProgramRequestDTO programRequestDTO) {

        if(!adminRepository.existsById(programRequestDTO.getAdminId())){
            throw new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.ADMIN,programRequestDTO.getAdminId()));
        }

        Program update = programRepository.findById(id).orElseThrow(
                ()-> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.PROGRAM,id)));

        if(MyUtils.isNotEmptyAndNotNull(programRequestDTO.getProgramName())){
            update.setProgramName(programRequestDTO.getProgramName());
        }

        if(MyUtils.isNotEmptyAndNotNull(programRequestDTO.getDescription())){
            update.setDescription(programRequestDTO.getDescription());
        }

        if(programRequestDTO.getProgramDuration()!=0){
            update.setProgramDuration(programRequestDTO.getProgramDuration());
        }

        programRepository.save(update);

        System.out.println(MyMessage.updated);

    }

    //DELETE============================================================================================================
    public void deleteProgram(Long programId,Long adminId) {

        if(!adminRepository.existsById(adminId)){
            throw new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.ADMIN,adminId));
        }
        programRepository.findById(programId).orElseThrow(
                () -> new MyResourceNotFoundException(MyMessage.resourceNotFound(ResourceType.PROGRAM, programId)));
        programRepository.deleteById(programId);
        System.out.println(MyMessage.deleted);
    }
}
