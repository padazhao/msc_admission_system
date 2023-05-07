package com.group13.msc_admission_system.controller;

import com.group13.msc_admission_system.dto.LoginCredentials;
import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import com.group13.msc_admission_system.dto.UserRequestDTO;
import com.group13.msc_admission_system.model.Admin;
import com.group13.msc_admission_system.model.Applicant;
import com.group13.msc_admission_system.model.Program;
import com.group13.msc_admission_system.service.serviceinterface.AdminService;
import com.group13.msc_admission_system.service.serviceinterface.ApplicantService;
import com.group13.msc_admission_system.service.serviceinterface.ProgramService;

import com.group13.msc_admission_system.service.serviceinterface.ProgramService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final ProgramService programService;
    private final ApplicantService applicantService;
    //CONSTRUCTOR=======================================================================================================
    @Autowired
    public AdminController(AdminService adminService,ProgramService programService,ApplicantService applicantService) {
        super();
        this.adminService = adminService;
        this.programService = programService;
        this.applicantService = applicantService;
    }

    //REGISTER==========================================================================================================
    @GetMapping("/register") // USED SHOW A REGISTRATION FORM
    public ModelAndView showAdminRegistrationForm(){
        ModelAndView modelAndView = new ModelAndView("Register");
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView register(@Validated @RequestParam UserRequestDTO userRequestDTO) {
        adminService.register(userRequestDTO);
        ModelAndView modelAndView = new ModelAndView("Login_form");
        return modelAndView;
    }

    //LOGIN=============================================================================================================
    @GetMapping("/login")
    public ModelAndView showAdminLoginForm(){
        ModelAndView modelAndView = new ModelAndView("school_admin_login");
        modelAndView.addObject("admin", new Admin());
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView adminLogin(@Validated LoginCredentials loginCredentials, Model model, HttpSession session){
        adminService.login(loginCredentials);
        Admin admin = adminService.getAdminInfoByEmail(loginCredentials.getEmail());
        ModelAndView modelAndView = new ModelAndView("school_admin_dashboard");
        modelAndView.addObject("user", admin);
        session.setAttribute("email", admin.getEmail());
        return modelAndView;
    }

    //HOME PAGE ========================================================================================================
    @GetMapping("/home_page")
    public ModelAndView showHomePage(HttpSession session){
        Admin admin = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("school_admin_dashboard");
        modelAndView.addObject("user", admin);
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
    //PROFILE PAGE =====================================================================================================
    @GetMapping("/home_page/AdminProfile")
    public ModelAndView showProfile(HttpSession session){
        Admin applicant = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));
        ModelAndView modelAndView = new ModelAndView("Admin_Profile");
        modelAndView.addObject("user", applicant);
        return modelAndView;
    }
    //Program Management =====================================================================================================
    @GetMapping("/home_page/ProgramManagement")
    public ModelAndView ProgramManagement(HttpSession session){
        Admin admin = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));

        ModelAndView modelAndView = new ModelAndView("ProgramManagement");
        modelAndView.addObject("user", admin);

        /* Show the program list to admin */
        List<Program> program = programService.getAllProgram();
        modelAndView.addObject("program",program);

        return modelAndView;
    }

    @PostMapping("/home_page/ProgramManagement/search")
    public ModelAndView ProgramManagement_search(HttpSession session,HttpServletRequest request){
        Admin admin = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));

        ModelAndView modelAndView = new ModelAndView("ProgramManagement");
        modelAndView.addObject("user", admin);

        /* Show the program list to admin */
        String temp = request.getParameter("PID");
        if(temp == ""){
            List<Program> program = programService.getAllProgram();
            modelAndView.addObject("program",program);
        }else{
            Long PID=Long.valueOf(temp);
            Program program = programService.getProgram(PID);
            modelAndView.addObject("program",program);
        }
        return modelAndView;
    }

    @PostMapping("/admin/home_page/ProgramManagement/create")
    public void ProgramManagement_create(HttpSession session,HttpServletRequest request){
        //加载 Hibernate 核心配置文件
        Configuration configuration = new Configuration().configure();
        //创建一个 SessionFactory 用来获取 Session 连接对象
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //获取session 连接对象
        Session sess = sessionFactory.openSession();
        //开始事务
        Transaction transaction = sess.beginTransaction();
        Program program = new Program();
        program.setProgramDuration(Long.valueOf(request.getParameter("Duration")));
        program.setDescription(request.getParameter("Description"));
        program.setProgramName(request.getParameter("Name"));
        Serializable save = (Serializable) sess.save(program);
        transaction.commit();
        //释放资源
        sess.close();
        sessionFactory.close();
    }

    //User Management =====================================================================================================
    @GetMapping("/home_page/UserManagement")
    public ModelAndView UserManagement(HttpSession session){
        Admin admin = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));

        ModelAndView modelAndView = new ModelAndView("UserManagement");
        modelAndView.addObject("user", admin);

        /* Show the user list to admin */
        List<Applicant> user = applicantService.getAllApplicantInfo();
        modelAndView.addObject("userInfo",user);

        return modelAndView;
    }


    @PostMapping("/home_page/UserManagement/search")
    public ModelAndView UserManagement_search(HttpSession session,HttpServletRequest request){
        Admin admin = adminService.getAdminInfoByEmail((String) session.getAttribute("email"));

        ModelAndView modelAndView = new ModelAndView("UserManagement");
        modelAndView.addObject("user", admin);

        /* Show the program list to admin */
        String temp = request.getParameter("UID");
        if(temp == ""){
            List<Applicant> applicants = applicantService.getAllApplicantInfo();
            modelAndView.addObject("userInfo",applicants);
        }else{
            Long UID=Long.valueOf(temp);
            Applicant applicants = applicantService.getApplicantInfo(UID);
            modelAndView.addObject("userInfo",applicants);
        }
        return modelAndView;
    }
}