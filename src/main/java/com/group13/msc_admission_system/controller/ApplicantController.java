package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/applicant")
public class ApplicantController {
    private final ApplicantService applicantService;

    //CONSTRUCTOR ======================================================================================================
    @Autowired
    public ApplicantController(ApplicantService applicantService) {this.applicantService = applicantService;}

    //REGISTER==========================================================================================================
    @GetMapping("/register")
    public ModelAndView showApplicantRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("applicant",new Applicant());
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView register(@Validated @RequestBody UserRequestDTO userRequestDTO){
        applicantService.register(userRequestDTO);
        ModelAndView modelAndView = new ModelAndView("login_form");
        return modelAndView;
    }

    //LOGIN=============================================================================================================
    @GetMapping("/login")
    public ModelAndView showLoginForm(){
        ModelAndView modelAndView = new ModelAndView("login_form");
        modelAndView.addObject("applicant", new Applicant());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@Validated @RequestBody LoginCredentials loginCredentials, Model model, HttpSession session){
        applicantService.login(loginCredentials);
        ModelAndView modelAndView = new ModelAndView("applicants");
        return modelAndView;
//        if (foundApplicant == null) {
//            model.addAttribute("error", "Invalid email or password");
//            return "login_form";
//        } else {
//            session.setAttribute("email", foundApplicant.getEmail());
//            return "redirect:/applicant";
//        }
    }

    //GET ==============================================================================================================
    @GetMapping("/info/{id}")
    public ModelAndView getApplicantInfo(@PathVariable("id") Long applicantId){
        Applicant applicant = applicantService.getApplicantInfo(applicantId);
        ModelAndView modelAndView = new ModelAndView("applicants");
        modelAndView.addObject("Applicant",applicant);
        return  modelAndView;
    }

    @GetMapping("/info/")
    public ModelAndView getAllApplicantInfo(){
        List<Applicant> applicant = applicantService.getAllApplicantInfo();
        ModelAndView modelAndView = new ModelAndView("applicants");
        modelAndView.addObject("Applicant",applicant);
        return  modelAndView;
    }

    //UPDATE============================================================================================================
    @PutMapping("/{id}")
    public ModelAndView updateUser(@Validated @PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) {
        applicantService.updateApplicant(id, userRequestDTO);
        return new ModelAndView("applicants");
    }
}
