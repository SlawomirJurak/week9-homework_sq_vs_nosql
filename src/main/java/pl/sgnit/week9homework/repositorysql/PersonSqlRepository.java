package pl.sgnit.week9homework.repositorysql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sgnit.week9homework.modelsql.PersonSql;

public interface PersonSqlRepository extends JpaRepository<PersonSql, Long> {
}
