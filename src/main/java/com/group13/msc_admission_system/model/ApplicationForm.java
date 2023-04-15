package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.common.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name= "application_Form")
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicationForm_id",nullable = false)
    private long applicationForm_id;

    @OneToOne
    @Column(name="applicant_id",nullable = false)
    private Applicant applicant;

    @OneToMany
    @Column(name = "programme_ids")
    private Set<Programme> programme;

    private Status status;
    private LocalDate applied_On;


    //CONSTRUCTOR==================================================
    public ApplicationForm(Applicant applicant, Set<Programme> programme){
        this.applicant = applicant;
        this.programme = programme;
        this.applied_On = LocalDate.now();
        this.status = Status.APPROVED;
    }

    public ApplicationForm() {

    }

    //GETTERS AND SETTERS===========================================

    public long getApplicationForm_id() {
        return applicationForm_id;
    }

    public Long getApplicant() {
        return applicant.getApplicant_id();
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

    public LocalDate getApplied_On() {
        return applied_On;
    }
}
