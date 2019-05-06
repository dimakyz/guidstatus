package guidstatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import guidstatus.entity.Entity;
import guidstatus.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
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
    @Autowired
    private ObjectMapper objectMapper;
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
    public void notGuidv2() throws Exception {
        mock.perform(
                get("/task/1b2a3c4d5f6e1b2a3c4d5f6g")
        ).andExpect(status().is(400));
    }

    @Test
    public void notGuidv3() throws Exception {
        mock.perform(
                get("/task/notguidv3")
        ).andExpect(status().is(400));
    }
    @Test
    public void getError() throws Exception {
        mock.perform(
                get("/task/1b2a3c4d5f6e1b2a3c4d5f6e")
        ).andExpect(status().is(404));
    }

    @Test
    public void getTask() throws Exception {
        Entity entity = taskService.create();
        String task = mock.perform(get(String.format("/task/%s", entity.getGuid()))
            ).andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        Entity receivedTask = objectMapper.readValue(task, Entity.class);

        assertEquals(entity.getGuid(), receivedTask.getGuid());
        assertEquals(entity.getDatetime(), receivedTask.getDatetime());
        assertEquals(entity.getStatus(), receivedTask.getStatus());
    }
}