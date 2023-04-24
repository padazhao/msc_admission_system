package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;

import com.group13.msc_admission_system.model.Program;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgramService {

    List<Program> getAllPrograms();

    void programUpdate(Long id, ProgramRequestDTO programRequestDTO);

    void deleteProgram(Long programId);

}
