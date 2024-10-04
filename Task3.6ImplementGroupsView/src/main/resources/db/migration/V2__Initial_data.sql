INSERT INTO department (department_name) VALUES ('Computer Science');
INSERT INTO department (department_name) VALUES ('Mathematics');

INSERT INTO teacher (department_id, first_name, last_name) VALUES (1, 'Adam', 'Smith');
INSERT INTO teacher (department_id, first_name, last_name) VALUES (2, 'Friedrich', 'Engels');

INSERT INTO groups (group_name) VALUES ('Group A');
INSERT INTO groups (group_name) VALUES ('Group B');

INSERT INTO classroom (classroom_name) VALUES ('Room 101');
INSERT INTO classroom (classroom_name) VALUES ('Room 102');

INSERT INTO course (course_name, course_description, teacher_id) VALUES ('Programming', 'Learn the basics of programming.', 1);
INSERT INTO course (course_name, course_description, teacher_id) VALUES ('Higher mathematics', 'Introduction to math.', 2);

INSERT INTO student (group_id, first_name, last_name) VALUES (1, 'Bob', 'Johnson');
INSERT INTO student (group_id, first_name, last_name) VALUES (2, 'Alice', 'Brown');

INSERT INTO timetable (timetable_date, start_time, end_time, course_id, room_id) VALUES ('2024-09-01', '08:00:00', '10:00:00', 1, 1);
INSERT INTO timetable (timetable_date, start_time, end_time, course_id, room_id) VALUES ('2024-09-01', '10:00:00', '12:00:00', 2, 2);

INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (2, 2);