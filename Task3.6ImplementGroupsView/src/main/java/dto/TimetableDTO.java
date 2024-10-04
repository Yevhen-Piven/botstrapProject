package dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TimetableDTO {
    private int timetableId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int courseId;
    private String courseName;
    private String description;
    private int teacherId;
    private String teacherName;
    private String teacherSurname;
    private int roomId;
    private String roomNumber;
    private int groupId;
    private String groupName;
    private int departmentId;
    private String departmentName;
    private int studentId;
    private String studentName;
    private String studentSurname;

    public TimetableDTO(int timetableId, LocalDate date, LocalTime startTime, LocalTime endTime, int courseId,
            String courseName, String description, int teacherId, String teacherName, String teacherSurname, int roomId,
            String roomNumber,  int groupId, String groupName, int departmentId, String departmentName,
            int studentId, String studentName, String studentSurname) {
        this.timetableId = timetableId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.groupId = groupId;
        this.groupName = groupName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
    }
}
