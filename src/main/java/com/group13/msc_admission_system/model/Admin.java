package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.dto.UserRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name="admin")
public class Admin extends User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long adminId;
//    @Column(name = "admin_username", nullable = false)
//    private String adminUsername;
//    @Column(name = "admin_password", nullable = false)
//    private String adminPassword;


    //CONSTRUCTOR ==============================================================
//    public Admin(String adminUsername, String adminPassword){
//        this.adminUsername = adminUsername;
//        this.adminPassword = adminPassword;
//    }

    public Admin(UserRequestDTO userRequestDTO){super(userRequestDTO);}
    public Admin() {}
//
//    //GETTERS AND SETTERS ==============================================================
//    public long getAdminId() {
//        return adminId;
//    }
//
//    public String getAdminUsername() {
//        return adminUsername;
//    }
//
//    public void setAdminUsername(String AdminUsername) {
//        this.adminUsername = AdminUsername;
//    }
//
//    public String getAdminPassword() {
//        return adminPassword;
//    }
//
//    public void setAdminPassword(String AdminPassword) {
//        this.adminPassword = AdminPassword;
//    }
}
