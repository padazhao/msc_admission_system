package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/applicationform")
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public ApplicationFormController(ApplicationFormService applicationFormService) { super();  this.applicationFormService = applicationFormService;}

    //CREATE AN APPLICATION FORM
    @PostMapping("/submit")
    public ModelAndView submitApplicationForm(ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.createApplication(applicationFormRequestDTO);
        return new ModelAndView("Application_form");
    }

    //UPDATE ===========================================================================================================
    @PutMapping("/program/{id}")
    public ModelAndView programFormUpdate(@PathVariable("id") Long id,
                                          @RequestBody ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.programUpdate(id,applicationFormRequestDTO);
        return new ModelAndView("applicant_form");
    }

    @PutMapping("/status/{id}")
    public ModelAndView statusUpdate(@PathVariable("id") Long id,
                               @RequestBody ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.statusUpdate(id,applicationFormRequestDTO);
        return new ModelAndView();
    }
}
