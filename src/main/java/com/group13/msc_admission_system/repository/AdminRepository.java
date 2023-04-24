package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
    Admin findByEmailAndPassword(String email, String password);
}
