package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.yevhenpiven.bootstrapproject.entity.Timetable;
import com.yevhenpiven.bootstrapproject.service.TimetableService;

@SpringBootTest(classes = BootstrapprojectApplication.class)
@AutoConfigureMockMvc
class TimetableControllerTest {

    private static final LocalDate FIRST_TEST_DATE = LocalDate.of(2023, 6, 1);
    private static final LocalTime FIRST_TEST_START_TIME = LocalTime.of(9, 0);
    private static final LocalTime FIRST_TEST_END_TIME = LocalTime.of(10, 0);
    private static final LocalDate SECOND_TEST_DATE = LocalDate.of(2023, 6, 2);
    private static final LocalTime SECOND_TEST_START_TIME = LocalTime.of(10, 0);
    private static final LocalTime SECOND_TEST_END_TIME = LocalTime.of(11, 0);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TimetableService timetableService;

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    void testListTimetables() throws Exception {
        Timetable timetable1 = new Timetable(FIRST_TEST_DATE, FIRST_TEST_START_TIME, FIRST_TEST_END_TIME);
        Timetable timetable2 = new Timetable(SECOND_TEST_DATE, SECOND_TEST_START_TIME, SECOND_TEST_END_TIME);
        List<Timetable> timetables = Arrays.asList(timetable1, timetable2);

        given(timetableService.findAll()).willReturn(timetables);

        String responseContent = mvc.perform(MockMvcRequestBuilders.get("/timetables").contentType("text/html"))
                .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse()
                .getContentAsString();

        assertTrue(responseContent.contains(FIRST_TEST_DATE.toString()));
        assertTrue(responseContent.contains(FIRST_TEST_START_TIME.toString()));
        assertTrue(responseContent.contains(FIRST_TEST_END_TIME.toString()));
        assertTrue(responseContent.contains(SECOND_TEST_DATE.toString()));
        assertTrue(responseContent.contains(SECOND_TEST_START_TIME.toString()));
        assertTrue(responseContent.contains(SECOND_TEST_END_TIME.toString()));

        verify(timetableService, times(1)).findAll();
    }
}
