package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ApplicantController {
    @Autowired private ApplicantService applicantService;



    public ApplicantController(ApplicantService applicantService) {
        super();
        this.applicantService = applicantService;
    }

    //register function
    @GetMapping("/register")
    public String showRegisterationForm(Model model){
        model.addAttribute("applicant", new Applicant());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @RequestBody ApplicantRequestDTO applicantRequestDTO){
        try{
            applicantService.register(applicantRequestDTO);
            return "redirect:/login";
        }catch (Exception e){
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("applicant", new Applicant());
        return "login_form";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("applicant") Applicant applicant, Model model){
        Applicant foundApplicant = applicantService.findByEmailAndPassword(applicant.getEmail(), applicant.getPassword());
        if (foundApplicant == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login_form";
        } else {
            return "redirect:/dashboard";
        }
    }





}
