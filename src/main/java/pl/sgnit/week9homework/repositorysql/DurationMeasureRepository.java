package pl.sgnit.week9homework.repositorysql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sgnit.week9homework.modelsql.DurationMeasure;
import pl.sgnit.week9homework.modelsql.OperationType;

import java.util.List;

public interface DurationMeasureRepository extends JpaRepository<DurationMeasure, Long> {

    DurationMeasure findByTryNoAndOperationType(Integer tryNo, OperationType operationType);

    List<DurationMeasure> findAllByOperationType(OperationType operationType);
}
