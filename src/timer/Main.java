package timer;

<<<<<<< Upstream, based on origin/Feature_3
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {	
		Application.launch(args);
=======
public class Main {
	public static void main(String[] args) {
		Duration duration = new Duration(0, 2, 0);
					
		duration.start();
>>>>>>> 7814ac0 Create classes for GUI
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
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