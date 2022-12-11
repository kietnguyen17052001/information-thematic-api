package com.example.informationthematicbackend.repository.jpa;

import com.example.informationthematicbackend.model.entity.ClassEntity;
import com.example.informationthematicbackend.model.entity.StudentClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClazzRepository extends JpaRepository<StudentClazzEntity, Long> {
    @Query("SELECT c FROM StudentClazzEntity sc" +
            " INNER JOIN sc.clazz c" +
            " ON sc.clazz.classId = c.classId" +
            " WHERE sc.student.userId = :studentId" +
            " ORDER BY sc.studentClassId DESC")
    List<ClassEntity> getCurrentClassForStudent(@Param("studentId") Long studentId);


    @Query("SELECT sc FROM StudentClazzEntity sc" +
            " LEFT JOIN FETCH sc.clazz" +
            " LEFT JOIN FETCH sc.student s" +
            " WHERE s.userId IN (:studentIds)" +
            " ORDER BY sc.studentClassId DESC")
    List<StudentClazzEntity> findByStudents(@Param("studentIds") List<Long> studentIds);

    @Query("SELECT sc FROM StudentClazzEntity sc" +
            " LEFT JOIN FETCH sc.clazz" +
            " WHERE sc.student.userId = :studentId")
    List<StudentClazzEntity> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT sc FROM StudentClazzEntity sc" +
            " LEFT JOIN FETCH sc.schoolYear" +
            " WHERE sc.clazz.classId = :classId" +
            " AND sc.student.userId = :studentId")
    StudentClazzEntity findByStudentIdAndClazzId(@Param("studentId") Long studentId, @Param("classId") Long classId);

    @Query("SELECT sc FROM StudentClazzEntity sc" +
            " LEFT JOIN FETCH sc.student" +
            " WHERE sc.clazz.classId = :clazzId")
    List<StudentClazzEntity> findByClazzId(@Param("clazzId") Long clazzId);
}