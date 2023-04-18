package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicationform")
public class ApplicationFormController {

    @Autowired ApplicationFormService applicationFormService;

    //CONSTRUCTOR=======================================================================================================
    public ApplicationFormController(ApplicationFormService applicationFormService) {
        super();
        this.applicationFormService = applicationFormService;
    }

    //UPDATE =======================================================================================================
    @PutMapping("/program/{id}")
    public String programFormUpdate(@PathVariable("id") Long id, @RequestBody ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.programUpdate(id,applicationFormRequestDTO);
        return "redirect: /dashboard";
    }

    @PutMapping("/status/{id}")
    public String statusUpdate(@PathVariable("id") Long id, @RequestBody ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.statusUpdate(id,applicationFormRequestDTO);
        return "redirect: /dashboard";
    }
}
