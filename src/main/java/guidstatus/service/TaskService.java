package guidstatus.service;

import guidstatus.entity.Entity;

public interface TaskService {
    Entity create();
    Entity update(Entity entity);
    Entity get(String id);
    void wait(Entity entity);
}
