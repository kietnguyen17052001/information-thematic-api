package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
}