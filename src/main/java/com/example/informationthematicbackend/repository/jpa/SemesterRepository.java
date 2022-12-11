package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.SemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<SemesterEntity, Long> {
}