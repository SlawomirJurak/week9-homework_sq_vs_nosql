package pl.sgnit.week9homework.modelsql;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DurationMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tryNo;

    private Long durationSql;

    private Long durationNoSql;

    @Enumerated()
    private OperationType operationType;

    public DurationMeasure() {
    }

    public DurationMeasure(Long id, Integer tryNo, Long durationSql, Long durationNoSql, OperationType operationType) {
        this.id = id;
        this.tryNo = tryNo;
        this.durationSql = durationSql;
        this.durationNoSql = durationNoSql;
        this.operationType = operationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTryNo() {
        return tryNo;
    }

    public void setTryNo(Integer tryNo) {
        this.tryNo = tryNo;
    }

    public Long getDurationSql() {
        return durationSql;
    }

    public void setDurationSql(Long durationSql) {
        this.durationSql = durationSql;
    }

    public Long getDurationNoSql() {
        return durationNoSql;
    }

    public void setDurationNoSql(Long durationNoSql) {
        this.durationNoSql = durationNoSql;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
