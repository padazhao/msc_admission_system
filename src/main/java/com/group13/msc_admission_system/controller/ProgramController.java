package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/program")
public class ProgramController {

     private final ProgramService programService;

    //CONSTRUCTOR====================================================================================================
    @Autowired
    public ProgramController(ProgramService programService) {   super(); this.programService = programService;}

    //UPDATE=========================================================================================================
    @PutMapping("/program/{id}")
    public String programUpdate(@PathVariable("id") Long id, @RequestBody ProgramRequestDTO programRequestDTO){
        programService.programUpdate(id,programRequestDTO);
        return "redirect: /dashboard";
    }

    //DELETE=========================================================================================================
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable("id") Long programId){
        programService.deleteProgram(programId);
        return "redirect: /dashboard";
    }
}
