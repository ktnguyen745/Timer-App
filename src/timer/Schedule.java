package timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Schedule {

	// Instance Variables
	private ArrayList<Task> tasks;
	private String name;
	private Time total;
	private GridPane displayPane;
	private VBox taskListBox;
	private ArrayList<Integer> swap;

	// Constructor
	public Schedule(String name) {
		this.name = name;
		tasks = new ArrayList<Task>();
		displayPane = new GridPane();
		swap = new ArrayList<Integer>();
	}

	// runSchedule
	public void runSchedule() {
		for(Task task : tasks) {
			task.run();
			displayPane.add(task.time().getTimer(), 0, 0);
			try {
				Thread.sleep((task.time().getHours() * 3600000) + 
						(task.time().getMinutes() * 60000) +
						(task.time().getSeconds() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task Complete");
		}
		System.out.println("Schedule Complete");
	}

	// AddTask
	public void addTask(String name, int hours, int minutes, int seconds) {
		Time time = new Time();
		time.setTimer(hours, minutes, seconds);
		Task task = new Task(name, time);
		tasks.add(task);
		taskListBox.getChildren().add(addTaskToGUI(name, hours, minutes, seconds));
		if (total != null) {
			int totalHours = hours + total.getHours();
			int totalMinutes = minutes + total.getMinutes();
			if(totalMinutes >= 60) {
				totalHours += (int) Math.floor(totalMinutes / 60);
				totalMinutes = totalMinutes % 60; 
			}
			int totalSeconds = seconds + total.getSeconds();
			if(totalSeconds >= 60) {
				totalMinutes += (int) Math.floor(totalSeconds / 60);
				totalSeconds = totalSeconds % 60;
			}
			total = new Time();
			total.setTimer(totalHours, totalMinutes, totalSeconds);
		} else {
			total = new Time();
			total.setTimer(hours, minutes, seconds);
		}
	}

	// RemoveTask
	public void removeTask(int index) {
		if(tasks.size() >= index) {
			int totalHours = total.getHours() - tasks.get(index).time().getHours();
			int totalMinutes = total.getMinutes() - tasks.get(index).time().getMinutes();
			if(totalMinutes < 0) {
				totalHours--;
				totalMinutes += 60;
			}
			int totalSeconds = total.getSeconds() - tasks.get(index).time().getSeconds();
			if(totalSeconds < 0) {
				totalMinutes--;
				totalSeconds += 60;
			}
			total = new Time();
			total.setTimer(totalHours, totalMinutes, totalSeconds);
			tasks.remove(index);	
		} 
		if(tasks.size() == 0) {
			total = new Time();
			total.setTimer(0, 0, 0);
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

	// Export to CSV file
	public void writeToCSV() throws FileNotFoundException {
		File csvFile = new File("schedule.csv");
		try(PrintWriter writer = new PrintWriter(csvFile)){
			for(Task task : tasks) {
				writer.write(task.toCSV() + "\n");
			}
		}
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
	public GridPane buildScheduleGUI() {
		VBox box = new VBox();
		box.setBackground(new Background (new BackgroundFill(
				Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));

		// Add top HBox
		HBox topBar = new HBox();

		Label scheduleLabel = new Label(name);
		scheduleLabel.setTextFill(Color.WHITE);
		scheduleLabel.setStyle("-fx-font-size: 2em; -fx-font-weight: bold;");
		scheduleLabel.setPrefWidth(200);

		Button addButton = new Button("Add Task");
		addButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		addButton.setTextFill(Color.WHITE);
		addButton.setStyle("-fx-font-weight: bold");
		addButton.setPrefSize(80, 40);

		Button csvButton = new Button("Create .csv");
		csvButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		csvButton.setTextFill(Color.WHITE);
		csvButton.setStyle("-fx-font-weight: bold");
		csvButton.setPrefSize(80, 40);

		Button deleteButton = new Button("Clear Tasks");
		deleteButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		deleteButton.setTextFill(Color.WHITE);
		deleteButton.setStyle("-fx-font-weight: bold");
		deleteButton.setPrefSize(80, 40);

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
				try {
					writeToCSV();
					Alert csvSuccess = new Alert(AlertType.INFORMATION);
					csvSuccess.setTitle("Success");
					csvSuccess.setHeaderText("A .csv file was created");
					csvSuccess.setContentText("Look for 'schedule.csv' in the project directory!");
					csvSuccess.showAndWait();
				} catch (FileNotFoundException e1) {
					Alert csvFail = new Alert(AlertType.ERROR);
					csvFail.setTitle("Error");
					csvFail.setHeaderText("An error has occurred");
					csvFail.setContentText("No .csv file could be created.");
					csvFail.showAndWait();
				}
			}
		};
		csvButton.setOnAction(exportCSVEvent);

		// This event will create a popup that takes a name, hour, minutes and seconds,
		// create a new task with those values and add it to the schedule.
		EventHandler<ActionEvent> addTaskEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Alert popup = new Alert(AlertType.INFORMATION);
				popup.setTitle("New Task");
				popup.setHeaderText("New Task Creation Dialog");
				
				GridPane grid = new GridPane();
				grid.setHgap(10);
				grid.setVgap(10);
				
				TextField nameField = new TextField();
				nameField.setPromptText("Enter task name...");
				
				TextField hoursField = new TextField();
				hoursField.setPromptText("How many hours?");
				
				TextField minutesField = new TextField();
				minutesField.setPromptText("How many minutes?");
				
				TextField secondsField = new TextField();
				secondsField.setPromptText("How many seconds?");
				
				grid.add(new Label("Name:"), 0, 0);
				grid.add(nameField, 1, 0);
				grid.add(new Label("Hours:"), 0, 1);
				grid.add(hoursField, 1, 1);
				grid.add(new Label("Minutes:"), 0, 2);
				grid.add(minutesField, 1, 2);
				grid.add(new Label("Seconds:"), 0, 3);
				grid.add(secondsField, 1, 3);
				
				popup.getDialogPane().setContent(grid);
				Optional<ButtonType> result = popup.showAndWait();		
				
				String name = "Task";
				if(nameField.getText() != null && nameField.getText().length() > 0) {
					name = nameField.getText();		
				} else {
					name = "Task " + (tasks.size() + 1);
				}
				
				int hours = 0;
				if(hoursField.getText() != null && hoursField.getText() != "") {
					try {
						hours = Integer.parseInt(hoursField.getText());
					} catch (NumberFormatException exception) {
						hours = 0;
					}
				} 
				
				int minutes = 0;
				if(minutesField.getText() != null && minutesField.getText() != "") {
					try {
						minutes = Integer.parseInt(minutesField.getText());
					} catch (NumberFormatException exception) {
						minutes = 0;
					}
				} 
				
				int seconds = 0;
				if(secondsField.getText() != null && secondsField.getText() != "") {
					try {
						seconds = Integer.parseInt(secondsField.getText());
					} catch (NumberFormatException exception) {
						seconds = 0;
					}
				} 
				
				if(result.isPresent() && result.get() == ButtonType.OK) addTask(name, hours, minutes, seconds);
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
		
		displayPane.add(box, 0, 1);

		return displayPane;
	}

	private HBox addTaskToGUI(String name, int hours, int minutes, int seconds) {
		
		Time time = new Time();
		time.setTimer(hours, minutes, seconds);

		HBox taskBox = new HBox();
		taskBox.setId(String.valueOf(taskListBox.getChildren().size()));
		
		DropShadow d = new DropShadow();
		d.setColor(Color.PALEVIOLETRED);;

		Button moveButton = new Button("Swap");
		moveButton.setPrefSize(60, 20);
		moveButton.setOnAction(getReorderTasksEvent());
		moveButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		moveButton.setId(String.valueOf(taskBox.getId()));
		
		moveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				moveButton.setEffect(d);
			}
		});

		Label taskName = new Label(name);
		taskName.setAlignment(Pos.CENTER);
		taskName.setStyle("-fx-font-size: 1.2em;");
		taskName.setPrefWidth(160);

		Label taskTime = new Label(time.toString());
		taskTime.setAlignment(Pos.CENTER);
		taskTime.setStyle("-fx-font-size: 1.2em;");
		taskTime.setPrefWidth(160);

		Button subButton = new Button("Remove");
		subButton.setPrefSize(60, 20);
		subButton.setOnAction(getRemoveTaskEvent());
		subButton.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		subButton.setId(String.valueOf(taskBox.getId()));

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
				String index = ((Button) e.getSource()).getId();
				swap.add(Integer.parseInt(index));
				if(swap.size() == 2) {
					reorderTasks(swap.get(0), swap.get(1));
					swap.clear();

					taskListBox.getChildren().clear();

					for(Task task : tasks) {
						taskListBox.getChildren().add(addTaskToGUI(task.name(), task.time().getHours(), 
								task.time().getMinutes(), task.time().getSeconds()));
					}
				}
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
				taskListBox.getChildren().remove(Integer.parseInt(index));
				
				taskListBox.getChildren().clear();

				for(Task task : tasks) {
					taskListBox.getChildren().add(addTaskToGUI(task.name(), task.time().getHours(), 
							task.time().getMinutes(), task.time().getSeconds()));
				}
			}
		};
		return removeTaskEvent;
	}

}
