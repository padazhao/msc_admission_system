package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {

}
