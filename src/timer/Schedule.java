package timer;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Schedule {
	
	// Instance Variables
	private ArrayList<Task> tasks;
	private String name;
	
	// Constructor
	public Schedule(String name) {
		this.name = name;
		tasks = new ArrayList<Task>();
	}
	
	// AddTask
	public void addTask(String name, Duration duration) {
		Task task = new Task(name, duration);
		tasks.add(task);
	}
	
	// RemoveTask
	public void removeTask() {
		if(tasks.size() > 0) {
			tasks.remove(0);	
		}
	}
	
	// ReorderTasks
	public boolean reorderTasks(int index1, int index2) {
		if(tasks.get(index1) != null && tasks.get(index2) != null) {
			Task tempTask = tasks.get(index2);
			tasks.set(index2, tasks.get(index1));
			tasks.set(index1, tempTask);
			return true; 
		}
		return false;
	}
	
	
	// Getter Methods
	public String name() {
		return name;
	}
	
	public ArrayList<Task> tasks(){
		return tasks;
	}
	
	// GUI Methods
	// 300x, 250y
	public VBox buildScheduleBox() {
		Stage stage = new Stage();
		Scene scene = new Scene(null, 270, 150);
		VBox box = new VBox();
		
		GridPane topBar = new GridPane();
		topBar.setPadding(new Insets(5, 5, 5, 5));
		
		Label scheduleLabel = new Label(name);
		scheduleLabel.setPrefWidth(170);
		
		Button addButton = new Button("+");
		addButton.setPrefSize(20, 20);
		
		Button csvButton = new Button("&");
		csvButton.setPrefSize(20, 20);
		
		Button deleteButton = new Button("X");
		deleteButton.setPrefSize(20, 20);
		
		topBar.add(scheduleLabel, 0, 0);
		topBar.add(addButton, 0, 1);
		topBar.add(csvButton, 0, 2);
		topBar.add(deleteButton, 0, 3);
		
		
		// This event will allow a button press to call the "export to csv" method
		EventHandler<ActionEvent> exportCSVEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
//				this.exportToCSV();
			}
		};
		
		// This event will create a popup that takes a name, hour, minutes and seconds,
		// create a new task with those values and add it to the schedule.
		EventHandler<ActionEvent> addTaskEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
			}
		};
		
		// This event will take the index of the task at the button clicked and remove that
		// task from the schedule.
		EventHandler<ActionEvent> removeTaskEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
			}
		};
		
		// This event will remove all tasks from the schedule.
		EventHandler<ActionEvent> clearScheduleEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				for(int i = 0; i < tasks.size(); i++) {
					removeTask();
				}
			}
		};
		
		// This event will wait for a user to click another reorder button and then swap those
		// two events in the schedule. 
		EventHandler<ActionEvent> reorderTasksEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
			}
		};
		
		return box;
	}
}
