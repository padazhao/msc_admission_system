package com.group13.msc_admission_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="programme")
public class Programme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="programme_id",nullable = false)
    private long programme_id;

    private String name;
    private String description;
    private long programme_Duration;

    //CONSTRUCTOR==============================================================
    public Programme(){

    }
    public Programme(String name, String description, long programme_Duration){
        this.name = name;
        this.description= description;
        this.programme_Duration = programme_Duration;
    }

    //GETTERS AND SETTERS
    public long getProgramme_id() {
        return programme_id;
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

    public long getProgramme_Duration() {
        return programme_Duration;
    }

    public void setProgramme_Duration(long programme_Duration) {
        this.programme_Duration = programme_Duration;
    }
}
