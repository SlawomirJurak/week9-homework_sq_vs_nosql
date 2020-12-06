package pl.sgnit.week9homework;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sgnit.week9homework.aspect.TimeMeasure;
import pl.sgnit.week9homework.modelnosql.PersonNoSql;
import pl.sgnit.week9homework.modelsql.DurationMeasure;
import pl.sgnit.week9homework.modelsql.OperationType;
import pl.sgnit.week9homework.modelsql.PersonSql;
import pl.sgnit.week9homework.repositorysql.DurationMeasureRepository;
import pl.sgnit.week9homework.repositorynosql.PersonNoSqlRepository;
import pl.sgnit.week9homework.repositorysql.PersonSqlRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Start {

    private final PersonNoSqlRepository personNoSqlRepository;
    private final PersonSqlRepository personSqlRepository;
    private final DurationMeasureRepository durationMeasureRepository;

    private final List<DurationMeasure> measureList = new ArrayList<>();

    public Start(PersonNoSqlRepository personNoSqlRepository, PersonSqlRepository personSqlRepository, DurationMeasureRepository durationMeasureRepository) {
        this.personNoSqlRepository = personNoSqlRepository;
        this.personSqlRepository = personSqlRepository;
        this.durationMeasureRepository = durationMeasureRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("url_to_mockarro_api",
            String.class);
        List<String[]> lines = createList(response);

        personNoSqlRepository.deleteAll();

        saveToSqlDatabase(lines);

        saveToNoSqlDatabase(lines);

        selectFromSqlDatabase();

        selectFromNoSqlDatabase();

    }

    public List<String[]> createList(String data) {
        List<String[]> list = new ArrayList<>();
        Scanner scanner = new Scanner(data);

        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine().split(","));
        }
        return list;
    }

    @TimeMeasure
    public void saveToSqlDatabase(List<String[]> data) {
        LocalDateTime start;
        LocalDateTime stop;

        for (int i=1; i<6; i++) {
            start = LocalDateTime.now();
            for (String[] line : data) {
                PersonSql personSql = new PersonSql(null, line[1], line[2], line[3], line[4], line[5]);

                personSqlRepository.save(personSql);
            }
            stop = LocalDateTime.now();
            Duration duration = Duration.between(start, stop);
            DurationMeasure durationMeasure = new DurationMeasure();
            durationMeasure.setTryNo(i);
            durationMeasure.setDurationSql(duration.toMillis());
            durationMeasure.setOperationType(OperationType.INSERT);
            durationMeasureRepository.save(durationMeasure);
        }
    }

    @TimeMeasure
    public void saveToNoSqlDatabase(List<String[]> data) {
        LocalDateTime start;
        LocalDateTime stop;

        for(int i=1; i<6; i++) {
            start = LocalDateTime.now();
            for (String[] line : data) {
                PersonNoSql personNoSql = new PersonNoSql(null, line[1], line[2], line[3], line[4], line[5]);

                personNoSqlRepository.save(personNoSql);
            }
            stop = LocalDateTime.now();
            DurationMeasure durationMeasure = durationMeasureRepository.findByTryNoAndOperationType(i, OperationType.INSERT);
            Duration duration = Duration.between(start, stop);
            durationMeasure.setDurationNoSql(duration.toMillis());
            durationMeasureRepository.save(durationMeasure);
        }
    }

    public void selectFromSqlDatabase() {
        LocalDateTime start;
        LocalDateTime stop;

        for (int i=1; i<6;i++) {
            start = LocalDateTime.now();
            List<PersonSql> list = personSqlRepository.findAll();
            stop = LocalDateTime.now();
            Duration duration = Duration.between(start, stop);
            DurationMeasure durationMeasure = new DurationMeasure();
            durationMeasure.setTryNo(i);
            durationMeasure.setDurationSql(duration.toMillis());
            durationMeasure.setOperationType(OperationType.SELECT);
            durationMeasureRepository.save(durationMeasure);
        }
    }

    public void selectFromNoSqlDatabase() {
        LocalDateTime start;
        LocalDateTime stop;

        for (int i=1; i<6; i++) {
            start = LocalDateTime.now();
            List<PersonNoSql> list = personNoSqlRepository.findAll();
            stop = LocalDateTime.now();
            DurationMeasure durationMeasure = durationMeasureRepository.findByTryNoAndOperationType(i, OperationType.SELECT);
            Duration duration = Duration.between(start, stop);
            durationMeasure.setDurationNoSql(duration.toMillis());
            durationMeasureRepository.save(durationMeasure);
        }
    }
}
