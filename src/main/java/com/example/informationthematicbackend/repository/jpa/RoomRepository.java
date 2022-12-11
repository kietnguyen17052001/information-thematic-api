package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("SELECT r FROM RoomEntity r" +
            " WHERE r.school.schoolId = :schoolId")
    List<RoomEntity> findBySchoolId(@Param("schoolId") Long schoolId);
}