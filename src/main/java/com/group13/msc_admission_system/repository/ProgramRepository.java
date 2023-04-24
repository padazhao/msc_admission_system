package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAll();
    Program findByProgramId(Long ProgramId);
}
