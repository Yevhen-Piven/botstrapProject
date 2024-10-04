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

import com.yevhenpiven.bootstrapproject.entity.Teacher;
import com.yevhenpiven.bootstrapproject.repository.TeacherRepository;
import com.yevhenpiven.bootstrapproject.service.TeacherService;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    private static final Integer TEACHER_ID = 1;
    private static final String TEACHER_FIRST_NAME_1 = "John";
    private static final String TEACHER_LAST_NAME_1 = "Doe";
    private static final String TEACHER_FIRST_NAME_2 = "Jane";
    private static final String TEACHER_LAST_NAME_2 = "Smith";
    private static final int DEPARTMENT_ID = 1;
    private static final int SECOND_DEPARTMENT_ID = 2;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    void testFindAll() {
        Teacher teacher1 = new Teacher(TEACHER_FIRST_NAME_1, TEACHER_LAST_NAME_1, DEPARTMENT_ID);
        Teacher teacher2 = new Teacher(TEACHER_FIRST_NAME_2, TEACHER_LAST_NAME_2, SECOND_DEPARTMENT_ID);

        when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacher1, teacher2));

        List<Teacher> teachers = teacherService.findAll();
        assertEquals(2, teachers.size());
        assertEquals(TEACHER_FIRST_NAME_1, teachers.get(0).getFirstName());
        assertEquals(TEACHER_LAST_NAME_1, teachers.get(0).getLastName());
    }

    @Test
    void testFindById() {
        Teacher teacher = new Teacher(TEACHER_FIRST_NAME_1, TEACHER_LAST_NAME_1, DEPARTMENT_ID);

        when(teacherRepository.findById(TEACHER_ID)).thenReturn(Optional.of(teacher));

        Optional<Teacher> foundTeacher = teacherService.findById(TEACHER_ID);
        assertTrue(foundTeacher.isPresent());
        assertEquals(TEACHER_FIRST_NAME_1, foundTeacher.get().getFirstName());
        assertEquals(TEACHER_LAST_NAME_1, foundTeacher.get().getLastName());
    }

    @Test
    void testSave() {
        Teacher teacher = new Teacher(TEACHER_FIRST_NAME_1, TEACHER_LAST_NAME_1, DEPARTMENT_ID);

        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher savedTeacher = teacherService.save(teacher);
        assertNotNull(savedTeacher);
        assertEquals(TEACHER_FIRST_NAME_1, savedTeacher.getFirstName());
        assertEquals(TEACHER_LAST_NAME_1, savedTeacher.getLastName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(teacherRepository).deleteById(TEACHER_ID);

        teacherService.deleteById(TEACHER_ID);
        verify(teacherRepository, times(1)).deleteById(TEACHER_ID);
    }
}
