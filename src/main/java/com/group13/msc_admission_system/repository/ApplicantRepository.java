package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    //@Query("from Applicant where email=:email")
    Applicant findByEmail(String email);

    Applicant findByEmailAndPassword(String email, String password);


}
