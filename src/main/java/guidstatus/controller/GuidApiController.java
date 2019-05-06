package guidstatus.controller;

import guidstatus.api.GuidApi;
import guidstatus.entity.Entity;
import guidstatus.service.TaskService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

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
    public ResponseEntity<Entity> getTask(@ApiParam(value = "guid") @PathVariable String id) {
        Entity entity = taskService.get(id);
        String errorString = null;
        try {
            BigInteger b = new BigInteger(id,16);
            System.out.println(b);
        }catch (Exception e){
            errorString = e.getMessage();
            System.out.println("wrong");
        }
        if(id.length() != 24 || errorString != null){
            return ResponseEntity.badRequest().build();
        } else
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }
}
