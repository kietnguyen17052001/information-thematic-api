package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.ExamResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT er FROM ExamResultEntity er" +
            " WHERE er.schoolYear.schoolYearId = :schoolYearId" +
            " AND er.semester.semesterId = :semesterId" +
            " AND er.subject.subjectId = :subjectId" +
            " AND er.student.userId = :studentId" +
            " AND er.examType = :type")
    Optional<ExamResultEntity> findFromDB(@Param("subjectId") Long subjectId, @Param("schoolYearId") Long schoolYearId,
                                          @Param("semesterId") Long semesterId, @Param("studentId") Long studentId, @Param("type") String type);
}