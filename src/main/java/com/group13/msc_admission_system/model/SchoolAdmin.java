package com.group13.msc_admission_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="school_admin")
public class SchoolAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_id;
    @Column(name = "school_admin_username", nullable = false)
    private String school_admin_username;
    @Column(name = "school_admin_password", nullable = false)
    private String school_admin_password;

    public SchoolAdmin(long admin_id, String school_admin_username, String school_admin_password){
        this.admin_id=admin_id;
        this.school_admin_username=school_admin_username;
        this.school_admin_password=school_admin_password;
    }
    public SchoolAdmin(){

    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }

    public String getSchool_admin_username() {
        return school_admin_username;
    }

    public void setSchool_admin_username(String school_admin_username) {
        this.school_admin_username = school_admin_username;
    }

    public String getSchool_admin_password() {
        return school_admin_password;
    }

    public void setSchool_admin_password(String school_admin_password) {
        this.school_admin_password = school_admin_password;
    }
}
