package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yevhenpiven.bootstrapproject.entity.Group;
import com.yevhenpiven.bootstrapproject.entity.Student;
import com.yevhenpiven.bootstrapproject.repository.StudentRepository;
import com.yevhenpiven.bootstrapproject.service.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private static final Integer STUDENT_ID = 1;
    private static final String STUDENT_FIRST_NAME_1 = "John";
    private static final String STUDENT_LAST_NAME_1 = "Doe";
    private static final String STUDENT_FIRST_NAME_2 = "Jane";
    private static final String STUDENT_LAST_NAME_2 = "Smith";
    private static final String GROUP_NAME = "Group 1";

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testFindAll() {
        Group group = new Group(GROUP_NAME);
        Student student1 = new Student(group, STUDENT_FIRST_NAME_1, STUDENT_LAST_NAME_1);
        Student student2 = new Student(group, STUDENT_FIRST_NAME_2, STUDENT_LAST_NAME_2);

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.findAll();
        assertEquals(2, students.size());
        assertEquals(STUDENT_FIRST_NAME_1, students.get(0).getFirstName());
        assertEquals(STUDENT_LAST_NAME_1, students.get(0).getLastName());
    }

    @Test
    void testFindById() {
        Group group = new Group(GROUP_NAME);
        Student student = new Student(group, STUDENT_FIRST_NAME_1, STUDENT_LAST_NAME_1);

        when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));

        Optional<Student> foundStudent = studentService.findById(STUDENT_ID);
        assertTrue(foundStudent.isPresent());
        assertEquals(STUDENT_FIRST_NAME_1, foundStudent.get().getFirstName());
        assertEquals(STUDENT_LAST_NAME_1, foundStudent.get().getLastName());
    }

    @Test
    void testSave() {
        Group group = new Group(GROUP_NAME);
        Student student = new Student(group, STUDENT_FIRST_NAME_1, STUDENT_LAST_NAME_1);

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.save(student);
        assertNotNull(savedStudent);
        assertEquals(STUDENT_FIRST_NAME_1, savedStudent.getFirstName());
        assertEquals(STUDENT_LAST_NAME_1, savedStudent.getLastName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(studentRepository).deleteById(STUDENT_ID);

        studentService.deleteById(STUDENT_ID);
        verify(studentRepository, times(1)).deleteById(STUDENT_ID);
    }
}
