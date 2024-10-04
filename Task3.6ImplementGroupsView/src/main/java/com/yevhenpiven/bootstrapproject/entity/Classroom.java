package com.yevhenpiven.bootstrapproject.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomId;

    @Column(name = "classroom_name")
    private String classroomName;

    @OneToMany(mappedBy = "classroom")
    private List<Timetable> timetables = new ArrayList<>();

    public Classroom() {
    }

    public Classroom( String classroomName) {
        this.classroomName = classroomName;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Classroom classroom = (Classroom) o;
        return classroomId == classroom.classroomId && Objects.equals(classroomName, classroom.classroomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, classroomName);
    }

    @Override
    public String toString() {
        return "Classroom{" + "classroomId=" + classroomId + ", classroomName='" + classroomName + '\''
                + ", timetables=" + timetables + '}';
    }
}