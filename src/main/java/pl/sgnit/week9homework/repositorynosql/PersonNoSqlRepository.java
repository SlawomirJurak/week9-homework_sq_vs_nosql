package pl.sgnit.week9homework.repositorynosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.sgnit.week9homework.modelnosql.PersonNoSql;

public interface PersonNoSqlRepository extends MongoRepository<PersonNoSql, String> {
}
