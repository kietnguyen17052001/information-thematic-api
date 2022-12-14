package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.ExamResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {
    @Query("SELECT er FROM ExamResultEntity er" +
            " LEFT JOIN FETCH er.learningResult lr" +
            " LEFT JOIN FETCH er.subject s" +
            " LEFT JOIN FETCH er.semester" +
            " WHERE lr.learningResultId = :learningResultId" +
            " AND s.subjectId IN (:subjectId)")
    List<ExamResultEntity> listExamResult(@Param("learningResultId") Long learningResultId,
                                          @Param("subjectIds") List<Long> subjectId);

}