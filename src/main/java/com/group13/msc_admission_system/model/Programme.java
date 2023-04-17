package com.group13.msc_admission_system.model;

import jakarta.persistence.*;

@Entity
@Table(name="programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="programme_id",nullable = false)
    private long programmeId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "duration", nullable = false)
    private long programmeDuration;

    //CONSTRUCTOR==============================================================
    public Programme(){

    }
    public Programme(String name, String description, long programmeDuration){
        this.name = name;
        this.description= description;
        this.programmeDuration = programmeDuration;
    }

    //GETTERS AND SETTERS
    public long getProgrammeId() {
        return programmeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProgrammeDuration() {
        return programmeDuration;
    }

    public void setProgrammeDuration(long programmeDuration) {
        this.programmeDuration = programmeDuration;
    }
}
