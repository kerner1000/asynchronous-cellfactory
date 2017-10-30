package com.github.acf;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExampleTable extends Application {

    private static final int NUM_ELEMENTS = 100;

    private final TableView<ExampleEntity> table = new TableView<>();

    private final ObservableList<ExampleEntity> data = FXCollections.observableArrayList();

    public static void main(final String[] args) {
	launch(args);
    }

    @Override
    public void start(final Stage stage) {
	final Scene scene = new Scene(new Group());
	stage.setTitle("Table View Sample");
	stage.setWidth(300);
	stage.setHeight(500);

	final TableColumn<ExampleEntity, String> c1 = new TableColumn<>("C1");
	final TableColumn<ExampleEntity, String> c2 = new TableColumn<>("C2");
	final TableColumn<ExampleEntity, String> c3 = new TableColumn<>("C3");

	c1.setPrefWidth(100);
	c2.setPrefWidth(100);
	c3.setPrefWidth(100);

	c1.setCellFactory(new DatabaseAccessCellFactory(1));
	c2.setCellFactory(new DatabaseAccessCellFactory(2));
	c3.setCellFactory(new DatabaseAccessCellFactory(3));

	for (int i = 0; i < NUM_ELEMENTS; i++) {
	    data.add(new ExampleEntity());
	}

	final ScrollPane sp = new ScrollPane();
	sp.setContent(table);
	sp.setMaxHeight(Double.POSITIVE_INFINITY);
	sp.setMaxWidth(Double.POSITIVE_INFINITY);
	sp.setFitToHeight(true);
	sp.setFitToWidth(true);

	table.setItems(data);
	// table.setMaxHeight(Double.POSITIVE_INFINITY);
	// table.setMaxWidth(Double.POSITIVE_INFINITY);
	table.getColumns().addAll(c1, c2, c3);

	final VBox vbox = new VBox();
	vbox.setSpacing(5);
	VBox.setVgrow(sp, Priority.ALWAYS);
	vbox.getChildren().addAll(sp);

	scene.setRoot(vbox);



	stage.setScene(scene);
	stage.show();
    }
}