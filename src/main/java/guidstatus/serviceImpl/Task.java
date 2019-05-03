package guidstatus.serviceImpl;

import guidstatus.entity.Entity;
import guidstatus.repository.EntityRepository;
import guidstatus.service.TaskService;
import guidstatus.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service("TaskService")
public class Task implements TaskService {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TimeService timeService;
    @Autowired
    private EntityRepository entityRepository;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Override
    public Entity create() {
        Entity entity = new Entity();
        entity.setStatus(Entity.Status.CREATED);
        entity.setDatetime(timeService.getDatetime());
        return entityRepository.save(entity);
    }

    @Override
    public Entity update(Entity entity) {
        return entityRepository.save(entity);
    }

    @Override
    public Entity get(String id) {
        return entityRepository.findByGuid(id);
    }

    @Override
    public void wait(Entity entity) {
        entity.setStatus(Entity.Status.RUNNING);
        entity.setDatetime(timeService.getDatetime());
        Entity newentity = entityRepository.save(entity);
        threadPoolTaskScheduler.schedule(
                new StatusChanger(newentity.getGuid(), Entity.Status.FINISHED, applicationContext),
                new Date(System.currentTimeMillis() + 120000));
    }
}
