package guidstatus;

import guidstatus.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = Main.class)
public class MainTest {
    @Autowired
    public MockMvc mock;
    @Autowired
    private TaskService taskService;

    @Test
    public void createTask() throws Exception {
        String id = mock.perform(post("/task")
        ).andExpect(status().is(202)).andReturn().getResponse().getContentAsString();
        assertNotNull(taskService.get(id));
    }

    @Test
    public void notGuid() throws Exception {
        mock.perform(
                get("/task/1b2a3c4d5f6e")
        ).andExpect(status().is(400));
    }
    @Test
    public void getError() throws Exception {
        mock.perform(
                get("/task/1b2a3c4d5f6e1b2a3c4d5f6e")
        ).andExpect(status().is(404));
    }
}