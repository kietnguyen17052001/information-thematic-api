package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.UserCalendarEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCalendarRepository extends JpaRepository<UserCalendarEventEntity, Long> {
    @Query("SELECT uc FROM UserCalendarEventEntity uc" +
            " LEFT JOIN FETCH uc.calendarEvent ce" +
            " LEFT JOIN FETCH ce.room" +
            " LEFT JOIN FETCH ce.subject" +
            " WHERE uc.user.userId = :userId")
    List<UserCalendarEventEntity> findListUserCalendar(@Param("userId") Long userId);

    @Query("SELECT uce FROM UserCalendarEventEntity uce" +
            " LEFT JOIN FETCH uce.user" +
            " WHERE uce.calendarEvent.calendarEventId = :calendarEventId")
    List<UserCalendarEventEntity> findByCalendar(@Param("calendarEventId") Long calendarEventId);

    @Query("SELECT uce FROM UserCalendarEventEntity uce" +
            " WHERE uce.user.userId = :studentId")
    List<UserCalendarEventEntity> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT uce FROM UserCalendarEventEntity uce" +
            " LEFT JOIN FETCH uce.calendarEvent ce" +
            " WHERE ce.calendarEventId IN (:calendarEventIds)")
    List<UserCalendarEventEntity> findByListCalendar(@Param("calendarEventIds") List<Long> calendarEventIds);
}