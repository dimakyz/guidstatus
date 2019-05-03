package guidstatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import guidstatus.service.TaskService;
import guidstatus.serviceImpl.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
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
}