package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.dto.ApplicantRequestDTO;
import com.group13.msc_admission_system.dto.ApplicantResponseDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class ApplicantController {
    @Autowired private ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    //register function
    @GetMapping("/register")
    public String showRegisterationForm(Model model){
        model.addAttribute("applicant", new Applicant());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated ApplicantRequestDTO applicantRequestDTO, Model model){
        try{
            applicantService.register(applicantRequestDTO);
            return "redirect:/";
        }catch (Exception e){
            String s = e.getMessage();
            model.addAttribute("err", s);
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("applicant", new Applicant());
        return "login_form";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("applicant") ApplicantRequestDTO applicantRequestDTO, Model model, HttpSession session){

        Applicant foundApplicant = applicantService.findByEmailAndPassword(applicantRequestDTO.getEmail(), applicantRequestDTO.getPassword());

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
    public String updateUser(@Validated @PathVariable("id") Long id, @RequestBody ApplicantRequestDTO applicantRequestDTO) {
        applicantService.updateApplicant(id, applicantRequestDTO);
        return "redirect:/dashboard";
    }
}
