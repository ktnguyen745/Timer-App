package timer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Executes the Schedule and CountdownTimer
 */
public class Main extends Application {
	public static void main(String[] args) {
		// Launch application
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Set the application size to 580 x 700
		GridPane root = new GridPane();
		Scene scene = new Scene(root, 580, 700);
		
		// Add a background to the application
		root.setStyle("-fx-background-image: url('background3.jpg'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");
		
		// Create a Schedule object
		Schedule schedule = new Schedule("Schedule");
		
		// Get the display for the CountdownTimer
		GridPane pane = new CountdownTimer(schedule).getTimer();

		// Add the CountdownTimer display to the application
		root.add(pane, 0, 0);
		
		// Centre the timer
		pane.setAlignment(Pos.CENTER);
		// Ensure the timer is in the right place
		root.setPadding(new Insets(180, 10, 10, 40));
		
		// Add the Schedule
		root.add(schedule.buildScheduleGUI(), 0, 1);
		// Ensure the Schedule is in the right place
		root.setVgap(170);
		
		// Add the icon image
		stage.getIcons().add(new Image("icon.jpg")); 
		// Add a name to the title bar
		stage.setTitle("   TimeBlock");
		// Disable the minimise/maximise button
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.setScene(scene);
		stage.show();
	}
}
