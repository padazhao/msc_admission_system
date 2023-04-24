package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.ApplicationForm;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.model.Admin;
import com.group13.msc_admission_system.repository.ApplicationFormRepository;
import com.group13.msc_admission_system.repository.ProgramRepository;
import com.group13.msc_admission_system.service.serviceinterface.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;
    private final ProgramRepository programRepository;
    private final ApplicationFormRepository applicationFormRepository;


    //CONSTRUCTOR=======================================================================================================================
    @Autowired
    public AdminController(ProgramRepository programRepository,
                                 ApplicationFormRepository applicationFormRepository,
                                 AdminService adminService) {
        super();
        this.programRepository = programRepository;
        this.applicationFormRepository = applicationFormRepository;
        this.adminService = adminService;
    }

    //REGISTER=======================================================================================================================================
    @GetMapping("/register") // USED SHOW A REGISTRATION FORM
    public ModelAndView showAdminRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Validated @RequestBody UserRequestDTO userRequestDTO) {
        adminService.register(userRequestDTO);
        ModelAndView modelAndView = new ModelAndView("login_form");
        return modelAndView;
    }

    //LOGIN===========================================================================================================================================
    @GetMapping("/login")
    public ModelAndView showAdminLoginForm(){
        ModelAndView modelAndView = new ModelAndView("school_admin_login");
        modelAndView.addObject("Admin", new Admin());
        return modelAndView;
    }

    //TODO SHOULD USE EMAIL AND PASSWORD INSTEAD, SINCE EMAIL IS UNIQUE AND NOT USERNAME
    @PostMapping("/login")
    public ModelAndView adminLogin(@Validated @RequestBody LoginCredentials loginCredentials){
        adminService.login(loginCredentials);
        ModelAndView modelAndView = new ModelAndView("school_admin_dashboard");
        return modelAndView;
    }




    //load all the programmes  information and all the applications information
    @GetMapping("/dashboard")
    public String adminProgram(){

        List<Program> programs = programRepository.findAll();
       // model.addAttribute("programmes", programs);
        List<ApplicationForm> applicationForms = applicationFormRepository.findAll();
       // model.addAttribute("applicationForms",applicationForms);
        ModelAndView modelAndView = new ModelAndView("school_admin_dashboard");
        modelAndView.addObject("progams",programs);
        modelAndView.addObject("applicationForms",applicationForms);
        return "school_admin_dashboard";

    }













    // find the programme by using programme_id in the html search button
    @GetMapping("/programme/search")
    public String getProgramById(Model model, long programId){
        Program programResult =programRepository.findByProgramId(programId);
        if (programResult == null) {
            return "programme_search_result";
        } else {
            model.addAttribute("programmeResult", programResult);
            return "programme_search_result";
        }

    }


    //load all the applications information
//    @GetMapping("/admin/dashboard")
//    public String adminApplication(Model model){
//        List<ApplicationForm> applicationForms = applicationFormRepository.findAll();
//        model.addAttribute("applicationForms",applicationForms);
//        return "school_admin_dashboard";
//    }

    // find the application by using applicationFormId in the html search button

    @GetMapping("/application/search")
    public String getApplicationFormById(Model model,long applicationFormId){
        ApplicationForm applicationFormResult=applicationFormRepository.findByApplicationFormId(applicationFormId);
        if (applicationFormResult == null) {
            return "application_search_result";
        } else {
            model.addAttribute("applicationFormResult",applicationFormResult);
            return "application_search_result";
        }

    }




}
