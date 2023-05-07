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
    @Column(name="applicationForm_id",nullable = false)
    private long applicationFormId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Applicant applicant;
    @ManyToMany
    private Set<Program> program;
    @Column(name = "status")
    private Status status;
    @Column(name = "appliedOn")
    private LocalDate appliedOn;


    //CONSTRUCTOR==================================================
    public ApplicationForm(Applicant applicant, Set<Program> program){
        this.applicant = applicant;
        this.program = program;
        this.appliedOn = LocalDate.now();
        this.status = Status.APPROVED;
    }
    public ApplicationForm() {}

    //GETTERS AND SETTERS===========================================

    public long getApplicationFormId() {
        return applicationFormId;
    }

    public Long getApplicant() {
        return applicant.getUserId();
    }

    public Set<Program> getProgram() {
        return program;
    }

    public void setProgram(Set<Program> program) {
        this.program = program;
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
