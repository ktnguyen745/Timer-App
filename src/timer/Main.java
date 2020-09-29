package timer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = new GridPane();

		Time time = new Time();
		Schedule schedule = new Schedule("Schedule");

		root.add(time.getTimer(), 0, 0);
		root.add(schedule.buildScheduleGUI(), 0, 1);

		Scene scene = new Scene(root, 500, 450);
		stage.setScene(scene);
		stage.show();
	}
}
