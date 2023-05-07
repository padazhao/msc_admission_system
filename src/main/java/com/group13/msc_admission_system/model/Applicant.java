package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.dto.UserRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name="applicant")
public class Applicant extends User{


    //TODO Use @CreationTimestamp to record timestamp in database and @UpdateTimestamp

    @OneToOne
    private ApplicationForm applicantForm;

    //CONSTRUCTOR=======================================================================================================
    public Applicant(UserRequestDTO userRequestDTO) {super(userRequestDTO);}

    public Applicant(){}

    public void setApplicantForm(ApplicationForm applicantForm) {
        this.applicantForm = applicantForm;
    }




}
