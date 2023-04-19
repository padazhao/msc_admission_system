package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.model.ApplicationForm;
import com.group13.msc_admission_system.model.Programme;
import com.group13.msc_admission_system.model.SchoolAdmin;
import com.group13.msc_admission_system.repository.ApplicationFormRepository;
import com.group13.msc_admission_system.repository.ProgrammeRepository;
import com.group13.msc_admission_system.service.serviceinterface.SchoolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SchoolAdminController {


    @Autowired
    private SchoolAdminService schoolAdminService;
    @Autowired
    private ProgrammeRepository programmeRepository;
    @Autowired
    private ApplicationFormRepository applicationFormRepository;


    public SchoolAdminController(ProgrammeRepository programmeRepository,
                                 ApplicationFormRepository applicationFormRepository,
                                 SchoolAdminService schoolAdminService) {
        super();

        this.programmeRepository = programmeRepository;
        this.applicationFormRepository = applicationFormRepository;
        this.schoolAdminService = schoolAdminService;
    }


    @GetMapping("/admin/login")
    public String showAdminLoginForm(Model model){
        model.addAttribute("schoolAdmin", new SchoolAdmin());
        return "school_admin_login";
    }

    @PostMapping("/admin/login")
    public String processAdminLoginForm(@ModelAttribute("schoolAdmin") SchoolAdmin schoolAdmin, Model model){
        SchoolAdmin foundAdmin = schoolAdminService.findSchoolAdminBySusernameAndpassword(schoolAdmin.getSchoolAdminUsername(),schoolAdmin.getSchoolAdminPassword());
        if (foundAdmin == null) {
            model.addAttribute("error", "Invalid email or password");
            return "school_admin_login";
        } else {
            return "redirect:/admin/dashboard";
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "redirect:/admin/login";
    }

//load all the programmes  information and all the applications information
    @GetMapping("/admin/dashboard")
    public String adminProgramme(Model model){
        List<Programme> programmes = programmeRepository.findAll();
        model.addAttribute("programmes", programmes);
        List<ApplicationForm> applicationForms = applicationFormRepository.findAll();
        model.addAttribute("applicationForms",applicationForms);
        return "school_admin_dashboard";

    }

    // find the programme by using programme_id in the html search button
    @GetMapping("/admin/programme/search")
    public String getProgrammeById(Model model,long programmeId){
        Programme programmeResult=programmeRepository.findByProgrammeId(programmeId);
        if (programmeResult == null) {
            return "programme_search_result";
        } else {
            model.addAttribute("programmeResult",programmeResult);
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

    //creat new programme by amdin
    @PostMapping("/admin/programme/create")
    public String createProgramme(@RequestParam("name") String name,
                                  @RequestParam("description") String description,
                                  @RequestParam("duration") long duration){
        Programme programme= new Programme(name, description, duration);
        programmeRepository.save(programme);
        return "redirect:/admin/dashboard";
    }
    // find the application by using applicationFormId in the html search button

    @GetMapping("/admin/application/search")
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
