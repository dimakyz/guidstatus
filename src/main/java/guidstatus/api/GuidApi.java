package guidstatus.api;

import guidstatus.entity.Entity;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Api(value = "task")
public interface GuidApi {

    @ApiOperation(value = "Receives `Entity`",
            nickname = "getTask",
            httpMethod = "GET",
            response = Entity.class,
            tags={ "task",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "`Entity`", response = Entity.class),
            @ApiResponse(code = 404, message = "`Entity` not founded")
    })
    @RequestMapping(value = "/task/{id}",
            method = RequestMethod.GET)
    ResponseEntity<Entity> getTask(@ApiParam(value = "guid") @PathVariable String id);
    @ApiOperation(value = "Create `Entity`",
            nickname = "createTask",
            httpMethod = "POST",
            response = String.class,
            tags={ "task",})
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "`Entity` was accepted")
    })
    @RequestMapping(value = "/task",
            method = RequestMethod.POST)
    ResponseEntity<String> createTask();
}
