package timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerGUI extends Application {
	private Label minutesLabel = new Label();
	private Label hoursLabel = new Label();
	private Label secondsLabel = new Label();
	private Timeline timeline = new Timeline();
	private Time time;
	
	private String format(Integer time) {
		if (time < 10) {
			return "0" + time.toString();
		}
		return time.toString();
	}
	
	public void setTimer(Time time) {
		this.time = time;
	}
	
	public GridPane getTimer() {	
		GridPane root = new GridPane();
		root.setVgap(7);
		Scene scene = new Scene(root, 500, 450);
		hoursLabel.setText(format(time.getHours()) + " :");
		hoursLabel.setTextFill(Color.WHITE);
		hoursLabel.setStyle("-fx-font-size: 4em;");
		minutesLabel.setText(format(time.getMinutes()) + " :");
		minutesLabel.setTextFill(Color.WHITE);
		minutesLabel.setStyle("-fx-font-size: 4em;");
		secondsLabel.setText(format(time.getSeconds()));
		secondsLabel.setTextFill(Color.WHITE);
		secondsLabel.setStyle("-fx-font-size: 4em;");
		Button button = new Button();
		button.setText("Start");
		button.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED, new CornerRadii(10), Insets.EMPTY)));
		button.setTextFill(Color.WHITE);
		button.setStyle("-fx-font-size: 1em;");
		
		DropShadow d = new DropShadow();
		d.setColor(Color.PALEVIOLETRED);;
		
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setEffect(d);
			}
		});
		button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				button.setEffect(null);
			}
		});
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				button.setOpacity(0.65);

				if (timeline != null) {
					timeline.stop();
				}
				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					// KeyFrame event handler
					public void handle(ActionEvent event) {
						if (time.getHours() == 0 && time.getMinutes() == 0 && time.getSeconds() == 0) {
							timeline.stop();
						}
						// update timerLabel
						hoursLabel.setText(format(time.getHours()) + " :");
						minutesLabel.setText(format(time.getMinutes()) + " :");
						secondsLabel.setText(format(time.getSeconds()));
						if (time.getHours() != 0 && time.getMinutes() == 0 && time.getSeconds() == 0) {
							// hours--;
							time.setHours(time.getHours() - 1);
							// minutes = 60;
							time.setMinutes(60);
						}
						if (time.getSeconds() == 0) {
							// seconds = 60;
							time.setSeconds(60);
							// minutes--;
							time.setMinutes(time.getMinutes() - 1);
						}
						// seconds--;
						time.setSeconds(time.getSeconds() - 1);
					}
				}));
				timeline.playFromStart();
			}
		});
		// Create and configure HBox
		// gap between components is 20
		HBox timerArea = new HBox(20);
		timerArea.setBackground(new Background(new BackgroundFill(Color.rgb(212, 117, 83), CornerRadii.EMPTY, Insets.EMPTY)));
		// center the components within HBox
		timerArea.setAlignment(Pos.CENTER);
		// Make it as wide as the application frame (scene)
		timerArea.setPrefWidth(scene.getWidth());
		// Move the HBox down a bit
		timerArea.setLayoutY(70);
		// Add the button and timerLabel to the HBox
		timerArea.getChildren().addAll(hoursLabel, minutesLabel, secondsLabel);
		// Add the HBox to the root component
		root.add(timerArea, 0, 0);
		HBox buttonsArea = new HBox(20);
		buttonsArea.setBackground(Background.EMPTY);
		buttonsArea.setPadding(new Insets(5,10,10,10));
		buttonsArea.setAlignment(Pos.CENTER);
		buttonsArea.getChildren().addAll(button);
		root.add(buttonsArea, 0, 1);
				
		return root;
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
		
		Time test1 = new Time();
		test1.setTimer(0, 1, 0);
		
		TimerGUI test = new TimerGUI();
		test.setTimer(test1);
		
		root.add(test.getTimer(), 0, 0);
			
		stage.setScene(scene);
		stage.show();

	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
	
}
