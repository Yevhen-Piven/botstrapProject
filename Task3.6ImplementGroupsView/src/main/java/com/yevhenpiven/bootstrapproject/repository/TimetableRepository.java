package com.yevhenpiven.bootstrapproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yevhenpiven.bootstrapproject.entity.Timetable;

import dto.TimetableDTO;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    @Query("SELECT new dto.TimetableDTO(t.timetableId, t.date, t.startTime, t.endTime, "
            + "c.courseId, c.courseName, c.courseDescription, " 
            + "te.teacherId, te.firstName, te.lastName,"
            + "cl.classroomId, cl.classroomName, " 
            + "g.groupId, g.groupName, "
            + "d.departmentId, d.departmentName, " 
            + "s.studentId, s.firstName, s.lastName) " 
            + "FROM Timetable t "
            + "JOIN t.course c " 
            + "JOIN c.teacher te " 
            + "JOIN c.students s " 
            + "JOIN s.group g "
            + "JOIN te.department d " 
            + "JOIN t.classroom cl " 
            + "WHERE t.date = :date")
    List<TimetableDTO> findTimetableByDate(@Param("date") LocalDate date);
}
