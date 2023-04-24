package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;


@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantService applicantService;

    //CONSTRUCTOR
    @Autowired
    public ApplicantController(ApplicantService applicantService) {this.applicantService = applicantService;}

    //register function
    @GetMapping("/register")
    public ModelAndView showApplicantRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView("register");
        //modelAndView.addObject("applicant",new Applicant());
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Validated @RequestBody UserRequestDTO userRequestDTO, Model model){
        applicantService.register(userRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("redirect:/login_form"));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("applicant", new Applicant());
        return "login_form";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("applicant") UserRequestDTO userRequestDTO, Model model, HttpSession session){

        Applicant foundApplicant = applicantService.findByEmailAndPassword(userRequestDTO.getEmail(), userRequestDTO.getPassword());

        if (foundApplicant == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login_form";
        } else {
            session.setAttribute("email", foundApplicant.getEmail());
            return "redirect:/applicant";
        }
    }





    //UPDATE==================================================================================================================================================
    @PutMapping("/applicant/{id}")
    public String updateUser(@Validated @PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
        applicantService.updateApplicant(id, userRequestDTO);
        return "redirect:/dashboard";
    }
}
