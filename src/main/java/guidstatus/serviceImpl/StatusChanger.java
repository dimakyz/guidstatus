package guidstatus.serviceImpl;

import guidstatus.entity.Entity;
import guidstatus.service.TaskService;
import guidstatus.service.TimeService;
import org.springframework.context.ApplicationContext;

public class StatusChanger implements Runnable {
    private TimeService timeService;
    private TaskService taskService;
    private String guid;
    private Entity.Status status;

    StatusChanger(String guid, Entity.Status status, ApplicationContext applicationContext) {
        this.guid = guid;
        this.status = status;
        this.timeService = applicationContext.getBean(TimeService.class);
        this.taskService = applicationContext.getBean(TaskService.class);
    }

    @Override
    public void run() {
        Entity entity = taskService.get(guid);
        entity.setDatetime(timeService.getDatetime());
        entity.setStatus(status);
        taskService.update(entity);
    }
}
