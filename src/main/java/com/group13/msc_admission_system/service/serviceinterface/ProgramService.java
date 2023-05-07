package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;

import com.group13.msc_admission_system.model.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgramService {

    void createProgram(ProgramRequestDTO programRequestDTO);

    Program getProgram(Long programId);
    List<Program> getAllProgram();

    void programUpdate(Long id, ProgramRequestDTO programRequestDTO);

    void deleteProgram(Long programId,Long adminId);


}
