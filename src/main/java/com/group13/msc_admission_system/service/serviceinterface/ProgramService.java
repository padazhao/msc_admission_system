package com.group13.msc_admission_system.service.serviceinterface;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;

import org.springframework.stereotype.Service;

@Service
public interface ProgramService {

    void programUpdate(Long id, ProgramRequestDTO programRequestDTO);

    void deleteProgram(Long programId);

}
