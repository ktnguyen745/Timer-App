package timer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		GridPane root = new GridPane();
		root.setPadding(new Insets(100, 10, 10, 10));
		root.setVgap(40);
		
		root.setStyle("-fx-background-image: url('background3.jpg'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");

		Scene scene = new Scene(root, 525, 450);
				
		Schedule schedule = new Schedule("Schedule");
		Time time = new Time(schedule);
		
		root.add(time.getTimer(), 0, 0);
		root.add(schedule.buildScheduleGUI(), 0, 1);
			
		stage.setScene(scene);
		stage.show();
	}
}
