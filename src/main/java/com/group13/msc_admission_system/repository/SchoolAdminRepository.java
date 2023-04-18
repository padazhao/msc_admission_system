package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.SchoolAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolAdminRepository extends JpaRepository<SchoolAdmin,Long> {

}
