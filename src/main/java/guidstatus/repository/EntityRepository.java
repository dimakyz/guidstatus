package guidstatus.repository;

import guidstatus.entity.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends MongoRepository<Entity, String> {
    Entity findByGuid(String guid);
}
