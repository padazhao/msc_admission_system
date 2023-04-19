package com.group13.msc_admission_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="school_admin")
public class SchoolAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;
    @Column(name = "school_admin_username", nullable = false)
    private String schoolAdminUsername;
    @Column(name = "school_admin_password", nullable = false)
    private String schoolAdminPassword;

    public SchoolAdmin(String schoolAdminUsername, String schoolAdminPassword){
        this.schoolAdminUsername=schoolAdminUsername;
        this.schoolAdminPassword=schoolAdminPassword;

    }
    public SchoolAdmin(){

    }

    public long getAdminId() {
        return adminId;
    }

    public String getSchoolAdminUsername() {
        return schoolAdminUsername;
    }

    public void setSchoolAdminUsername(String schoolAdminUsername) {
        this.schoolAdminUsername = schoolAdminUsername;
    }

    public String getSchoolAdminPassword() {
        return schoolAdminPassword;
    }

    public void setSchoolAdminPassword(String schoolAdminPassword) {
        this.schoolAdminPassword = schoolAdminPassword;
    }
}
