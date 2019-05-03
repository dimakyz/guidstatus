package guidstatus.controller;

import guidstatus.api.GuidApi;
import guidstatus.entity.Entity;
import guidstatus.service.TaskService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuidApiController implements GuidApi {
    @Autowired
    private TaskService taskService;

    @Override
    public ResponseEntity<String> createTask() {
        Entity entity = taskService.create();
        taskService.wait(entity);
        return ResponseEntity.accepted().body(entity.getGuid());
    }

    @Override
    public ResponseEntity<Entity> getTask(@ApiParam(value = "GUID") @PathVariable String id) {
        Entity entity = taskService.get(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }


}
