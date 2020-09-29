package timer;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Schedule {

	ArrayList<Integer> swap = new ArrayList<Integer>();
	VBox taskListBox;

	// Instance Variables
	private ArrayList<Task> tasks;
	private String name;

	// Constructor
	public Schedule(String name) {
		this.name = name;
		tasks = new ArrayList<Task>();
	}

	// AddTask
	public void addTask(String name, Time time) {
		Task task = new Task(name, time);
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
	public VBox buildScheduleGUI() {
		VBox box = new VBox();
		box.setBackground(new Background (new BackgroundFill(
				Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));

		// Add top HBox
		HBox topBar = new HBox();

		Label scheduleLabel = new Label(name);
		scheduleLabel.setTextFill(Color.WHITE);
		scheduleLabel.setStyle("-fx-font-size: 1.5em; -fx-font-weight: bold;");
		scheduleLabel.setPrefWidth(200);

		Button addButton = new Button("+");
		addButton.setPrefSize(30, 20);

		Button csvButton = new Button("&");
		csvButton.setPrefSize(30, 20);

		Button deleteButton = new Button("X");
		deleteButton.setPrefSize(30, 20);

		HBox.setMargin(scheduleLabel, new Insets(5, 5, 5, 15));
		HBox.setMargin(addButton, new Insets(5, 5, 5, 5));
		HBox.setMargin(csvButton, new Insets(5, 5, 5, 5));
		HBox.setMargin(deleteButton, new Insets(5, 5, 5, 5));

		topBar.getChildren().add(scheduleLabel);
		topBar.getChildren().add(addButton);
		topBar.getChildren().add(csvButton);
		topBar.getChildren().add(deleteButton);

		box.getChildren().add(topBar);

		// Add ScrollPane
		ScrollPane taskPane = new ScrollPane();
		taskPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		taskPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);

		taskListBox = new VBox();
		taskPane.setContent(taskListBox);

		box.getChildren().add(taskPane);

		// This event will allow a button press to call the "export to csv" method
		EventHandler<ActionEvent> exportCSVEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				exportToCSV();
			}
		};
		csvButton.setOnAction(exportCSVEvent);

		// This event will create a popup that takes a name, hour, minutes and seconds,
		// create a new task with those values and add it to the schedule.
		EventHandler<ActionEvent> addTaskEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String name = "Test Task";
				int hours = 0;
				int minutes = 2;
				int seconds = 14;	

				taskListBox.getChildren().add(addTaskToGUI(name, hours, minutes, seconds));
			}
		};
		addButton.setOnAction(addTaskEvent);

		// This event will remove all tasks from the schedule.
		EventHandler<ActionEvent> clearScheduleEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				tasks.clear();
				taskListBox.getChildren().clear();
			}
		};
		deleteButton.setOnAction(clearScheduleEvent);

		return box;
	}

	private HBox addTaskToGUI(String name, int hours, int minutes, int seconds) {
		Time time = new Time();
		time.setTimer(hours, minutes, seconds);
		addTask(name, time);

		HBox taskBox = new HBox();
		taskBox.setId(String.valueOf(tasks.size() - 1));

		Button moveButton = new Button("=");
		moveButton.setPrefSize(30, 20);
		moveButton.setOnAction(getReorderTasksEvent());
		moveButton.setId(String.valueOf(tasks.size() - 1));

		Label taskName = new Label(name);
		taskName.setStyle("-fx-font-size: 1.2em;");
		taskName.setPrefWidth(120);

		Label taskTime = new Label("HH:MM:SS");
		taskTime.setStyle("-fx-font-size: 1.2em;");
		taskTime.setPrefWidth(120);

		Button subButton = new Button("-");
		subButton.setPrefSize(30, 20);
		subButton.setOnAction(getRemoveTaskEvent());
		subButton.setId(String.valueOf(tasks.size() - 1));

		HBox.setMargin(moveButton, new Insets(5, 5, 5, 5));
		HBox.setMargin(taskName, new Insets(5, 5, 5, 5));
		HBox.setMargin(taskTime, new Insets(5, 5, 5, 5));
		HBox.setMargin(subButton, new Insets(5, 5, 5, 5));

		taskBox.getChildren().add(moveButton);
		taskBox.getChildren().add(taskName);
		taskBox.getChildren().add(taskTime);
		taskBox.getChildren().add(subButton);
		
		return taskBox;
	}

	private EventHandler<ActionEvent> getReorderTasksEvent(){
		// This event will wait for a user to click another reorder button and then swap those
		// two events in the schedule. 
		EventHandler<ActionEvent> reorderTasksEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
//				String index = ((Button) e.getSource()).getId();
//				swap.add(Integer.parseInt(index));
//				if(swap.size() == 2) {
//					reorderTasks(swap.get(0), swap.get(1));
//					swap.clear();
//					
//					taskListBox.getChildren().clear();
//					
//					for(Task task : tasks) {
//						addTaskToGUI(task.name(), task.time().getHours(), task.time().getMinutes(),
//								task.time().getSeconds());
//					}
//				}
			}
		};
		return reorderTasksEvent;
	}

	private EventHandler<ActionEvent> getRemoveTaskEvent(){
		// This event will take the index of the task at the button clicked and remove that
		// task from the schedule.
		EventHandler<ActionEvent> removeTaskEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String index = ((Button) e.getSource()).getId();
				removeTask(Integer.parseInt(index));
			}
		};
		return removeTaskEvent;
	}

}
