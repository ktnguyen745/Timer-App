package timer;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Time extends Application {
	// Change to final at some point
//	private static final int secondsDelay = 1000;
//	private static final int secondsPeriod = 1000;

	private Integer hours = 1;
	private Integer minutes = 0;
	private Integer seconds = 2;

	private Label minutesLabel = new Label();
	private Label hoursLabel = new Label();
	private Label secondsLabel = new Label();

//	private Timer timer = new Timer();
	private Timeline timeline = new Timeline();


	public void setTimer(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds + 1;
	}

//	Old constructor
//	public Duration(int hours, int minutes, int seconds) {
//		this.hours = hours;
//		this.minutes = minutes;
//		this.seconds = seconds + 1;
//	}

//	public void start() {
//		timer.scheduleAtFixedRate(new TimerTask() {
//
//			public void run() {
//				timerLabel.setText(seconds.toString());
//				
//				System.out.println(getHours() + " : " + getMinutes() + " : " + setInterval());
//			}
//		}, secondsDelay, secondsPeriod);
//	}
//	
//	private final int setInterval() {
//	if (hours == 0 && minutes == 0 && seconds == 1) {
//		timer.cancel();
//	}
//
//	switch (seconds) {
//	case 1:
//		if (hours != 0 && minutes == 0) {
//			--hours;
//			minutes = 59;
//		} else {
//			--minutes;
//		}
//
//		break;
//	case 0:
//		seconds = 60;
//		break;
//	}
//
//	return --seconds;
//}
//
//	public int getHours() {
//		return hours;
//	}
//
//	public int getMinutes() {
//		return minutes;
//	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		hoursLabel.setText(hours.toString() + " :");
		hoursLabel.setTextFill(Color.WHITE);
		hoursLabel.setStyle("-fx-font-size: 4em;");
		
		minutesLabel.setText(minutes.toString() + " :");
		minutesLabel.setTextFill(Color.WHITE);
		minutesLabel.setStyle("-fx-font-size: 4em;");
		
		secondsLabel.setText(seconds.toString());
		secondsLabel.setTextFill(Color.WHITE);
		secondsLabel.setStyle("-fx-font-size: 4em;");

		Button button = new Button();
		button.setText("Start");
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (timeline != null) {
					timeline.stop();
				}

				// update timerLabel
				secondsLabel.setText(seconds.toString());
				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					// KeyFrame event handler
					public void handle(ActionEvent event) {								
						if (hours == 0 && minutes == 0 && seconds == 0) {
							timeline.stop();
						}

						// update timerLabel
						hoursLabel.setText(hours.toString() + " :");
						minutesLabel.setText(minutes.toString() + " :");
						secondsLabel.setText(seconds.toString());

						if(hours != 0 && minutes == 0 && seconds == 0) {
							hours--;
							minutes = 60;
						}
						
						if(seconds == 0) {
							seconds = 60;
							minutes--;
						}
						
						seconds--;
					}
				}));
				timeline.playFromStart();
			}
		});
		
		// Create and configure VBox
		// gap between components is 20
		HBox hbox = new HBox(20);
		hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK,
			    CornerRadii.EMPTY,
			    Insets.EMPTY)));
		// center the components within VBox
		hbox.setAlignment(Pos.CENTER);
		// Make it as wide as the application frame (scene)
		hbox.setPrefWidth(scene.getWidth());
		// Move the VBox down a bit
		hbox.setLayoutY(30);
		// Add the button and timerLabel to the VBox
		hbox.getChildren().addAll(hoursLabel, minutesLabel, secondsLabel, button);
		// Add the VBox to the root component
		root.getChildren().add(hbox);
	}
}
