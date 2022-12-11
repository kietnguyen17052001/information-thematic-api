package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.ClassCalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassCalendarRepository extends JpaRepository<ClassCalendarEventEntity, Long> {
    @Query("SELECT cce FROM ClassCalendarEventEntity cce" +
            " LEFT JOIN FETCH cce.calendarEvent ce" +
            " LEFT JOIN FETCH ce.room" +
            " LEFT JOIN FETCH ce.subject" +
            " LEFT JOIN FETCH cce.schoolYear" +
            " LEFT JOIN FETCH cce.semester" +
            " WHERE cce.clazz.classId = :classId")
    List<ClassCalendarEventEntity> listClassCalendarEvent(@Param("classId") Long classId);

    @Query("SELECT cce FROM ClassCalendarEventEntity cce" +
            " LEFT JOIN FETCH cce.clazz" +
            " WHERE cce.calendarEvent.calendarEventId = :calendarEventId")
    List<ClassCalendarEventEntity> findByCalendar(@Param("calendarEventId") Long calendarEventId);

    @Query("SELECT cce FROM ClassCalendarEventEntity cce" +
            " LEFT JOIN FETCH cce.clazz" +
            " WHERE cce.calendarEvent.calendarEventId IN (:calendarEventIds)")
    List<ClassCalendarEventEntity> findByCalendarIds(@Param("calendarEventIds") List<Long> calendarEventIds);
}