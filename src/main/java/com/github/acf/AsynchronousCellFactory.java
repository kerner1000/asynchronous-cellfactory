package com.github.acf;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public abstract class AsynchronousCellFactory<E, T> implements Callback<TableColumn<E, T>, TableCell<E, T>> {


    @Override
    public TableCell<E, T> call(final TableColumn<E, T> param) {
	final TableCell<E, T> cell = new TableCell<E, T>() {

	    private Service<T> service;

	    @Override
	    public void updateItem(final T item, final boolean empty) {

		super.updateItem(item, empty);
		if (service != null) {
		    service.cancel();
		}
		if (empty || this.getTableRow() == null || this.getTableRow().getItem() == null) {
		    setText(null);
		} else {
		    setText("Calculating..");
		    final E rowDataItem = (E) this.getTableRow().getItem();
		    service = new Service<T>() {

			@Override
			protected Task<T> createTask() {
			    return getTask(rowDataItem);
			}
		    };
		    service.setOnSucceeded(e -> {
			if (e.getSource().getValue() == null) {
			    setText("n/a");
			} else {
			    setText(e.getSource().getValue().toString());
			}
		    });
		    service.setOnFailed(e -> {
			final Throwable t = e.getSource().getException();
			setText(t.getLocalizedMessage());
		    });
		    service.start();
		}
	    }
	};

	return cell;
    }

    protected abstract Task<T> getTask(E rowDataItem);

}
