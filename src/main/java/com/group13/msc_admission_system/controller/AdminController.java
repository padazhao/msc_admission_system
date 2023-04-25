package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Admin;
import com.group13.msc_admission_system.service.serviceinterface.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public AdminController(AdminService adminService) { super();    this.adminService = adminService;}

    //REGISTER==========================================================================================================
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

    //LOGIN=============================================================================================================
    @GetMapping("/login")
    public ModelAndView showAdminLoginForm(){
        ModelAndView modelAndView = new ModelAndView("school_admin_login");
        modelAndView.addObject("Admin", new Admin());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView adminLogin(@Validated @RequestBody LoginCredentials loginCredentials){
        adminService.login(loginCredentials);
        ModelAndView modelAndView = new ModelAndView("school_admin_dashboard");
        return modelAndView;
    }
}
