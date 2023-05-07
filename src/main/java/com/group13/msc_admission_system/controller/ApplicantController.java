package com.group13.msc_admission_system.controller;


import com.group13.msc_admission_system.common.Message;
import com.group13.msc_admission_system.common.ResourceType;
import com.group13.msc_admission_system.dto.ApplicationFormRequestDTO;
import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.exception.MyResourceNotFoundException;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import com.group13.msc_admission_system.service.serviceinterface.ApplicationFormService;
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
    private final ApplicationFormService applicationFormService;

    //CONSTRUCTOR ======================================================================================================
    @Autowired
    public ApplicantController(ApplicantService applicantService, ProgramService programService, ApplicationFormService applicationFormService) {
        this.applicantService = applicantService;
        this.programService = programService;
        this.applicationFormService = applicationFormService;
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
        ModelAndView modelAndView = new ModelAndView("Login_form");

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
        session.setAttribute("id", applicant.getUserId());
        return modelAndView;
    }

    //GET ==============================================================================================================

    //HOME PAGE ========================================================================================================
    @GetMapping("/home_page")
    public ModelAndView showHomePage(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo((Long) session.getAttribute("id"));

        ModelAndView modelAndView = new ModelAndView("Applicants");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }

    //PROFILE PAGE =====================================================================================================
    @GetMapping("/home_page/profile")
    public ModelAndView showProfile(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo((Long) session.getAttribute("id"));

        ModelAndView modelAndView = new ModelAndView("Profile");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }
    //CONTACT PAGE =====================================================================================================
    @GetMapping("/home_page/contact")
    public ModelAndView showContact(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo((Long) session.getAttribute("id"));

        ModelAndView modelAndView = new ModelAndView("Contact");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }

    //SUBMISSION PAGE ==================================================================================================
    @GetMapping("/home_page/application_form")
    public ModelAndView showApplicationForm(HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo((Long) session.getAttribute("id"));

        ModelAndView modelAndView = new ModelAndView("Application_form");
        modelAndView.addObject("user", applicant);

        /* Show the program list to applicant */
        List<Program> program = programService.getAllProgram();
        modelAndView.addObject("program",program);

        return modelAndView;
    }

    //CREATE AN APPLICATION FORM
    @PostMapping("/home_page/application_form")
    public ModelAndView submitApplicationForm(ApplicationFormRequestDTO applicationFormRequestDTO, HttpSession session){
        applicationFormService.createApplication(applicationFormRequestDTO);

        Applicant applicant = applicantService.getApplicantInfo((Long) session.getAttribute("id"));
        ModelAndView modelAndView = new ModelAndView("Application_form");
        modelAndView.addObject("user", applicant);

        List<Program> program = programService.getAllProgram();
        modelAndView.addObject("program",program);
        return modelAndView;
    }


    //SETTING PAGE & UPDATE=============================================================================================
    @GetMapping("/home_page/settings/{id}")
    public ModelAndView showSettings(@PathVariable("id") Long applicantId, HttpSession session){
        Applicant applicant = applicantService.getApplicantInfo(applicantId);
        
        ModelAndView modelAndView = new ModelAndView("Settings");
        modelAndView.addObject("user", applicant);

        return modelAndView;
    }

    @PostMapping("/home_page/settings/{id}")
    public ModelAndView updateUser(@Validated @PathVariable("id") Long id, UserRequestDTO userRequestDTO) {
        Applicant updatedApplicant = applicantService.updateApplicant(id, userRequestDTO);

        ModelAndView modelAndView = new ModelAndView("Profile");
        modelAndView.addObject("user", updatedApplicant);

        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView programUpdate(@PathVariable("id") Long id, @RequestBody ProgramRequestDTO programRequestDTO){
        programService.programUpdate(id,programRequestDTO);
        return new ModelAndView();
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

    //UPDATE============================================================================================================
/*    @GetMapping("/info/{id}")
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
    }*/
}
