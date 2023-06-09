package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return new ModelAndView("index");
    }

    //GET ==============================================================================================================
    @GetMapping("/info/{id}")
    public ModelAndView getProgramInfo(@PathVariable("id") Long programId){
        Program program = programService.getProgram(programId);
        ModelAndView modelAndView = new ModelAndView("applicants");
        modelAndView.addObject("program",program);
        return  modelAndView;
    }

    // 我改了url
/*    @GetMapping("/applicant/home_page/application_form")
    public ModelAndView getAllProgram(HttpSession session){
        List<Program> program = programService.getAllProgram();
        ModelAndView modelAndView = new ModelAndView("Application_form");
        modelAndView.addObject("program",program);
        return  modelAndView;
    }*/

    //UPDATE============================================================================================================
    @PutMapping("/{id}")
    public ModelAndView programUpdate(@PathVariable("id") Long id, @RequestBody ProgramRequestDTO programRequestDTO){
        programService.programUpdate(id,programRequestDTO);
        return new ModelAndView();
    }

    //DELETE============================================================================================================
    @DeleteMapping("/{programId}/{adminId}")
    public ModelAndView deleteProgram(@PathVariable("programId") Long programId,@PathVariable("adminId") Long adminId){
        programService.deleteProgram(programId,adminId);
        return new ModelAndView();
    }
}
