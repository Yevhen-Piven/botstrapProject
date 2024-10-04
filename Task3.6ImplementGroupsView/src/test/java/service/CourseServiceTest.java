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

import com.yevhenpiven.bootstrapproject.entity.Course;
import com.yevhenpiven.bootstrapproject.repository.CourseRepository;
import com.yevhenpiven.bootstrapproject.service.CourseService;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    private static final String FIRST_COURSE_NAME = "Course 1";
    private static final String SECOND_COURSE_NAME = "Course 2";
    private static final String SECOND_COURSE_DESCRIPTION = "Description 2";
    private static final String FIRST_COURSE_DESCRIPTION = "Description 1";
    private static final int COURSE_ID = 1;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testFindAll() {
        Course course1 = new Course(FIRST_COURSE_NAME, FIRST_COURSE_DESCRIPTION);
        Course course2 = new Course(SECOND_COURSE_NAME, SECOND_COURSE_DESCRIPTION);

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        List<Course> courses = courseService.findAll();
        assertEquals(2, courses.size());
        assertEquals(FIRST_COURSE_NAME, courses.get(0).getCourseName());
    }

    @Test
    void testFindById() {
        Course course = new Course(FIRST_COURSE_NAME, FIRST_COURSE_DESCRIPTION);
        when(courseRepository.findById(COURSE_ID)).thenReturn(Optional.of(course));

        Optional<Course> foundCourse = courseService.findById(COURSE_ID);
        assertTrue(foundCourse.isPresent());
        assertEquals(FIRST_COURSE_NAME, foundCourse.get().getCourseName());
    }

    @Test
    void testSave() {
        Course course = new Course(FIRST_COURSE_NAME, FIRST_COURSE_DESCRIPTION);
        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseService.save(course);
        assertNotNull(savedCourse);
        assertEquals(FIRST_COURSE_NAME, savedCourse.getCourseName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(courseRepository).deleteById(COURSE_ID);
        courseService.deleteById(COURSE_ID);
        verify(courseRepository, times(1)).deleteById(COURSE_ID);
    }
}
