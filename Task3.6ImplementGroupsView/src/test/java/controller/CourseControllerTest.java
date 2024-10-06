package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.yevhenpiven.bootstrapproject.BootstrapprojectApplication;
import com.yevhenpiven.bootstrapproject.entity.Course;
import com.yevhenpiven.bootstrapproject.service.CourseService;

@SpringBootTest(classes = BootstrapprojectApplication.class)
@AutoConfigureMockMvc
class CourseControllerTest {
    private static final String FIRST_TEST_COURSE_NAME = "Course 1";
    private static final String FIRST_TEST_COURSE_DESCRIPTION = "Description 1";
    private static final String SECOND_TEST_COURSE_NAME = "Course 2";
    private static final String SECOND_TEST_COURSE_DESCRIPTION = "Description 2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testListCourses() throws Exception {
        Course course1 = new Course(FIRST_TEST_COURSE_NAME, FIRST_TEST_COURSE_DESCRIPTION);
        Course course2 = new Course(SECOND_TEST_COURSE_NAME, SECOND_TEST_COURSE_DESCRIPTION);
        List<Course> allCourses = Arrays.asList(course1, course2);

        given(courseService.findAll()).willReturn(allCourses);

        String responseContent = mvc.perform(MockMvcRequestBuilders.get("/courses").contentType("text/html"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString();

        assertTrue(responseContent.contains(FIRST_TEST_COURSE_NAME));
        assertTrue(responseContent.contains(SECOND_TEST_COURSE_NAME));
        verify(courseService, times(1)).findAll();
    }
}
