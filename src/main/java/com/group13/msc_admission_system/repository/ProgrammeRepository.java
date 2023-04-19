package com.group13.msc_admission_system.repository;

import com.group13.msc_admission_system.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    List<Programme> findAll();
    Programme findByProgrammeId(Long ProgrammeId);
}
