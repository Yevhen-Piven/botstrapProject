package controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.yevhenpiven.bootstrapproject.BootstrapprojectApplication;

@SpringBootTest(classes = BootstrapprojectApplication.class)
@AutoConfigureMockMvc
class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testWelcome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").contentType("text/html")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}
