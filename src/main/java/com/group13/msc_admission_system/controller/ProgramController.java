package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public ProgramController(ProgramService programService) {   super(); this.programService = programService;}

    //CREATE============================================================================================================
    @PostMapping("/create")
    public ModelAndView createProgram(@Validated @RequestBody ProgramRequestDTO programRequestDTO){
        programService.createProgram(programRequestDTO);
        return new ModelAndView();
    }

    //GET ==============================================================================================================
    @GetMapping("/info/{id}")
    public ModelAndView getProgramInfo(@PathVariable("id") Long programId){
        Program program = programService.getProgram(programId);
        ModelAndView modelAndView = new ModelAndView("applicants");
        modelAndView.addObject("program",program);
        return  modelAndView;
    }

    @GetMapping("/info/")
    public ModelAndView getAllProgram(){
        List<Program> program = programService.getAllProgram();
        ModelAndView modelAndView = new ModelAndView("applicants");
        modelAndView.addObject("program",program);
        return  modelAndView;
    }

    //UPDATE============================================================================================================
    @PutMapping("/{id}")
    public ModelAndView programUpdate(@PathVariable("id") Long id, @RequestBody ProgramRequestDTO programRequestDTO){
        programService.programUpdate(id,programRequestDTO);
        return new ModelAndView();
    }

    //DELETE============================================================================================================
    @DeleteMapping("/{id}")
    public ModelAndView deleteProgram(@PathVariable("id") Long programId){
        programService.deleteProgram(programId);
        return new ModelAndView();
    }
}
