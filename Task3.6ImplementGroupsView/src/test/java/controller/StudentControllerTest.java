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
import com.yevhenpiven.bootstrapproject.entity.Group;
import com.yevhenpiven.bootstrapproject.entity.Student;
import com.yevhenpiven.bootstrapproject.service.StudentService;

@SpringBootTest(classes = BootstrapprojectApplication.class)
@AutoConfigureMockMvc
class StudentControllerTest {

    private static final String FIRST_TEST_STUDENT_SURNAME = "Student sur 1";
    private static final String FIRST_TEST_STUDENT_NAME = "Student 1";
    private static final String SECOND_TEST_STUDENT_NAME = "Student 2";
    private static final String SECOND_TEST_STUDENT_SURNAME = "Student sur 2";
    private static final Group FIRST_TEST_GROUP_ID = new Group();
    private static final Group SECOND_TEST_GROUP_ID = new Group();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    void testListStudents() throws Exception {
        Student student1 = new Student(FIRST_TEST_GROUP_ID, FIRST_TEST_STUDENT_NAME, FIRST_TEST_STUDENT_SURNAME);
        Student student2 = new Student(SECOND_TEST_GROUP_ID, SECOND_TEST_STUDENT_NAME, SECOND_TEST_STUDENT_SURNAME);
        List<Student> students = Arrays.asList(student1, student2);
        given(studentService.findAll()).willReturn(students);

        String responseContent = mvc.perform(MockMvcRequestBuilders.get("/students").contentType("text/html"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString();

        assertTrue(responseContent.contains(FIRST_TEST_STUDENT_NAME));
        assertTrue(responseContent.contains(SECOND_TEST_STUDENT_NAME));
        assertTrue(responseContent.contains(FIRST_TEST_STUDENT_SURNAME));
        assertTrue(responseContent.contains(SECOND_TEST_STUDENT_SURNAME));

        verify(studentService, times(1)).findAll();
    }
}
