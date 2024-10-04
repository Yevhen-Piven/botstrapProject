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

import com.yevhenpiven.bootstrapproject.entity.Classroom;
import com.yevhenpiven.bootstrapproject.repository.ClassroomRepository;
import com.yevhenpiven.bootstrapproject.service.ClassroomService;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceTest {
    private static final String FIRST_CLASSROOM_NAME = "Classroom 1";
    private static final String SECOND_CLASSROOM_NAME = "Classroom 2";
    private static final int CLASSROOM_ID = 1;

    @Mock
    private ClassroomRepository classroomRepository;

    @InjectMocks
    private ClassroomService classroomService;

    @Test
    void testFindAll() {
        Classroom classroom1 = new Classroom(FIRST_CLASSROOM_NAME);
        Classroom classroom2 = new Classroom(SECOND_CLASSROOM_NAME);

        when(classroomRepository.findAll()).thenReturn(Arrays.asList(classroom1, classroom2));

        List<Classroom> classrooms = classroomService.findAll();
        assertEquals(2, classrooms.size());
        assertEquals(FIRST_CLASSROOM_NAME, classrooms.get(0).getClassroomName());
    }

    @Test
    void testFindById() {
        Classroom classroom = new Classroom(FIRST_CLASSROOM_NAME);
        when(classroomRepository.findById(CLASSROOM_ID)).thenReturn(Optional.of(classroom));

        Optional<Classroom> foundClassroom = classroomService.findById(CLASSROOM_ID);
        assertTrue(foundClassroom.isPresent());
        assertEquals(FIRST_CLASSROOM_NAME, foundClassroom.get().getClassroomName());
    }

    @Test
    void testSave() {
        Classroom classroom = new Classroom(FIRST_CLASSROOM_NAME);
        when(classroomRepository.save(classroom)).thenReturn(classroom);

        Classroom savedClassroom = classroomService.save(classroom);
        assertNotNull(savedClassroom);
        assertEquals(FIRST_CLASSROOM_NAME, savedClassroom.getClassroomName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(classroomRepository).deleteById(CLASSROOM_ID);
        classroomService.deleteById(CLASSROOM_ID);
        verify(classroomRepository, times(1)).deleteById(CLASSROOM_ID);
    }
}
