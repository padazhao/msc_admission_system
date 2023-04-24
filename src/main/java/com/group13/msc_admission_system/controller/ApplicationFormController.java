package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicationform")
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
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

    //TO BE USED AS EXAMPLE ON HOW TO RETURN A RESPONSE ENTITY BUT WITH A HEADER TO REDIRECT
//    @GetMapping("/example")
//    public ResponseEntity<String> example() {
//        String responseBody = "This is the response body";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("/redirected-page"));
//        return new ResponseEntity<>(responseBody, headers, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/redirected-page")
//    public String redirectedPage() {
//        return "redirected-page";
//    }
}
