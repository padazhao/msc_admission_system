package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final ProgramService programService;

    //CONSTRUCTOR ======================================================================================================
    @Autowired
    public ApplicantController(ApplicantService applicantService, ProgramService programService) {
        this.applicantService = applicantService;
        this.programService = programService;
    }

    //REGISTER==========================================================================================================
    @GetMapping("/register")
    public ModelAndView showApplicantRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView("Register");
        modelAndView.addObject("applicant",new Applicant());

        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView register(@Validated UserRequestDTO userRequestDTO){
        applicantService.register(userRequestDTO);
        ModelAndView modelAndView = new ModelAndView("index");

        return modelAndView;
    }

    //LOGIN=============================================================================================================
    @GetMapping("/login")
    public ModelAndView showLoginForm(){
        ModelAndView modelAndView = new ModelAndView("Login_form");
        modelAndView.addObject("applicant", new Applicant());

        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@Validated LoginCredentials loginCredentials, Model model, HttpSession session) {
        /* Verify the existing applicant */
        applicantService.login(loginCredentials);
        /* Use email find exist applicant */
        Applicant applicant = applicantService.getApplicantInfoByEmail(loginCredentials.getEmail());
        ModelAndView modelAndView = new ModelAndView("Applicants");
        modelAndView.addObject("user", applicant);
        /* store the email of corresponding applicant */
        session.setAttribute("email", applicant.getEmail());
        return modelAndView;
    }

//        if (foundApplicant == null) {
//            model.addAttribute("error", "Invalid email or password");
//            return "login_form";
//        } else {
//            session.setAttribute("email", foundApplicant.getEmail());
//            return "redirect:/applicant";
//        }


    //GET ==============================================================================================================

    //HOME PAGE ========================================================================================================
    @GetMapping("/home_page")
    public ModelAndView showHomePage(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("Applicants");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }

    //PROFILE PAGE =====================================================================================================
    @GetMapping("/home_page/profile")
    public ModelAndView showProfile(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("Profile");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }
    //CONTACT PAGE =====================================================================================================
    @GetMapping("/home_page/contact")
    public ModelAndView showContact(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("Contact");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }

    //SUBMISSION PAGE ==================================================================================================
    @GetMapping("/home_page/application_form")
    public ModelAndView showApplicationForm(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("Application_form");
        modelAndView.addObject("user", applicant);

        List<Program> program = programService.getAllProgram();
        modelAndView.addObject("program",program);
        return modelAndView;
    }

    //SETTING PAGE ====================================================================================================
    @GetMapping("/home_page/settings/{id}")
    public ModelAndView showSettings(@PathVariable("id") Long applicantId, HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo(applicantId);
        /*Applicant applicant = applicantService.getApplicantInfoByEmail((String) session.getAttribute("email"));*/
        ModelAndView modelAndView = new ModelAndView("Settings");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }
    //LOG OUT ==========================================================================================================
    @GetMapping("/")
    public ModelAndView logout(HttpServletRequest request) {
        /* End session after log out */
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/info/{id}")
    public ModelAndView getApplicantInfo(@PathVariable("id") Long applicantId){
        Applicant applicant = applicantService.getApplicantInfo(applicantId);
        ModelAndView modelAndView = new ModelAndView("Applicants");
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
    @PutMapping("/home_page/settings/{id}")
    public ModelAndView updateUser(@Validated @PathVariable("id") Long id, UserRequestDTO userRequestDTO) {
        applicantService.updateApplicant(id, userRequestDTO);
        ModelAndView modelAndView = new ModelAndView("Applicants");

        return modelAndView;
    }
}
