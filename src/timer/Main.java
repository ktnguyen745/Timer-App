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
		// TODO Auto-generated method stub
		GridPane root = new GridPane();

		Time time = new Time();

		root.add(time.getTimer(), 0, 0);

		
		Scene scene = new Scene(root, 500, 450);
		stage.setScene(scene);
		stage.show();
	}
}
