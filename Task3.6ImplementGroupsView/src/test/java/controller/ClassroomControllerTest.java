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
import com.yevhenpiven.bootstrapproject.entity.Classroom;
import com.yevhenpiven.bootstrapproject.service.ClassroomService;

@SpringBootTest(classes = BootstrapprojectApplication.class)
@AutoConfigureMockMvc
class ClassroomControllerTest {

    private static final String FIRST_TEST_CLASSROOM_NAME = "Classroom 1";
    private static final String SECOND_TEST_CLASSROOM_NAME = "Classroom 2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClassroomService classroomService;

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void testListClassroomsWithUserRole() throws Exception {
        Classroom classroom1 = new Classroom(FIRST_TEST_CLASSROOM_NAME);
        Classroom classroom2 = new Classroom(SECOND_TEST_CLASSROOM_NAME);
        List<Classroom> allClassrooms = Arrays.asList(classroom1, classroom2);

        given(classroomService.findAll()).willReturn(allClassrooms);

        String responseContent = mvc.perform(MockMvcRequestBuilders.get("/classrooms").contentType("text/html"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString();

        assertTrue(responseContent.contains(FIRST_TEST_CLASSROOM_NAME));
        assertTrue(responseContent.contains(SECOND_TEST_CLASSROOM_NAME));
        verify(classroomService, times(1)).findAll();
    }
}
