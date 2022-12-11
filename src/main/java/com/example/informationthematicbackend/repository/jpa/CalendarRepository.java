package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.CalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEventEntity, Long> {
}