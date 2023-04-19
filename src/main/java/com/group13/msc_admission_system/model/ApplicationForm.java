package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.common.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name= "application_Form")
public class ApplicationForm {

    //ATTRIBUTES =========================================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_form_id",nullable = false)
    private long applicationFormId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Applicant applicant;

    @OneToMany
    private Set<Programme> programme;
    @Column(name = "status")
    private Status status;
    @Column(name = "applied_On")
    private LocalDate appliedOn;


    //CONSTRUCTOR==================================================
    public ApplicationForm(Applicant applicant, Set<Programme> programme){
        this.applicant = applicant;
        this.programme = programme;
        this.appliedOn = LocalDate.now();
        this.status = Status.APPROVED;
    }
    public ApplicationForm() {

    }

    //GETTERS AND SETTERS===========================================

    public long getApplicationFormId() {
        return applicationFormId;
    }

    public Long getApplicant() {
        return applicant.getApplicantId();
    }


    public Set<Programme> getProgramme() {
        return programme;
    }

    public void setProgramme(Set<Programme> programme) {
        this.programme = programme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getAppliedOn() {
        return appliedOn;
    }
}
