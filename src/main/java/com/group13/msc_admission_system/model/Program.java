package com.group13.msc_admission_system.model;

import com.group13.msc_admission_system.dto.ProgramRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name="program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="program_id",nullable = false)
    private long programId;
    @Column(name = "program_name", nullable = false)
    private String programName;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "duration", nullable = false)
    private long programDuration;

    //CONSTRUCTOR==============================================================
    public Program(ProgramRequestDTO programRequestDTO){
        this.programName = programRequestDTO.getProgramName();
        this.description= programRequestDTO.getDescription();
        this.programDuration = programRequestDTO.getProgramDuration();
    }
    public Program(){}

    //GETTERS AND SETTERS
    public long getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProgramDuration() {
        return programDuration;
    }

    public void setProgramDuration(long programDuration) {
        this.programDuration = programDuration;
    }
}
