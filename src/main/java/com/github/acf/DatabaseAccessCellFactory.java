package com.github.acf;

import javafx.concurrent.Task;

public class DatabaseAccessCellFactory extends AsynchronousCellFactory<ExampleEntity, String> {

    private final int index;

    public DatabaseAccessCellFactory(final int index) {
	super();
	this.index = index;
    }

    @Override
    protected Task<String> getTask(final ExampleEntity rowDataItem) {
	return new Task<String>() {

	    @Override
	    protected String call() throws Exception {
		// simulate db access
		Thread.sleep(20);
		return Integer.toString(index);
	    }
	};
    }

}
