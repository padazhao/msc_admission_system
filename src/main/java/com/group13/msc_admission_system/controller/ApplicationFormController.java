package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationFormController {

    @Autowired ApplicationFormService applicationFormService;

    public ApplicationFormController(ApplicationFormService applicationFormService) {
        super();
        this.applicationFormService = applicationFormService;
    }

    //UPDATE USER
    @PutMapping("/{id}")
    public String updateApplicationForm(@PathVariable("id") Long id, @RequestBody ApplicationFormRequestDTO applicationFormRequestDTO){
        applicationFormService.updateApplicationForm(id,applicationFormRequestDTO);
        return "redirect: /dashboard";
    }
}
