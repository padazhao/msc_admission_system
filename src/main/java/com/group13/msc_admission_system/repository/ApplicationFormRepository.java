package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;
@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
    List<ApplicationForm> findAll();
    ApplicationForm findByApplicationFormId(Long ApplicationFormId);
=======
@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long> {

>>>>>>> origin/master
}
