package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
    @Query("SELECT s FROM SchoolEntity s" +
            " WHERE s.school = :name" +
            " AND s.city = :city")
    Optional<SchoolEntity> findSchoolByNameAndCity(@Param("name") String name, @Param("city") String city);
}