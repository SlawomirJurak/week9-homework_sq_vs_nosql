package pl.sgnit.week9homework.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.sgnit.week9homework.modelsql.DurationMeasure;
import pl.sgnit.week9homework.modelsql.OperationType;
import pl.sgnit.week9homework.repositorysql.DurationMeasureRepository;

@Route("")
public class MainView extends VerticalLayout {

    private final DurationMeasureRepository durationMeasureRepository;

    private Grid<DurationMeasure> gridInsert = new Grid<>(DurationMeasure.class);;
    private Grid<DurationMeasure> gridSelect = new Grid<>(DurationMeasure.class);;

    public MainView(DurationMeasureRepository durationMeasureRepository) {
        this.durationMeasureRepository = durationMeasureRepository;
        createView();
    }

    public void createView() {
        setSizeFull();
        configureGrid(gridInsert);
        gridInsert.setItems(durationMeasureRepository.findAllByOperationType(OperationType.INSERT));
        configureGrid(gridSelect);
        gridSelect.setItems(durationMeasureRepository.findAllByOperationType(OperationType.SELECT));
        add(new H1("Measure insert time"), gridInsert,
            new H1("Measure select time"), gridSelect);
    }

    private void configureGrid(Grid grid) {
        grid.setWidthFull();
        grid.removeColumnByKey("id");
        grid.setColumns("tryNo", "durationSql", "durationNoSql");
        grid.getColumnByKey("tryNo").setHeader("Try No");
        grid.getColumnByKey("durationSql").setHeader("PostgreSql [ms]");
        grid.getColumnByKey("durationNoSql").setHeader("MongoDb [ms]");
        grid.setItems(durationMeasureRepository.findAll());
    }
}
