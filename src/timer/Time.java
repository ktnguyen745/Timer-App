package timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class Time {
	private Integer hours = 1;
	private Integer minutes = 0;
	private Integer seconds = 2;
	
	private boolean go = false;

	private Label minutesLabel = new Label();
	private Label hoursLabel = new Label();
	private Label secondsLabel = new Label();

	private Timeline timeline = new Timeline();

	public void setTimer(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	private String format(Integer time) {
		if (time < 10) {
			return "0" + time.toString();
		}

		return time.toString();
	}


	public GridPane getTimer() {
		Integer initialHours = hours;
		Integer initialMinutes = minutes;
		Integer initialSeconds = seconds;

		
		GridPane root = new GridPane();
		root.setPadding(new Insets(80, 10, 10, 10));
		root.setVgap(7);

		Scene scene = new Scene(root, 500, 450);

		hoursLabel.setText(format(initialHours) + " :");
		hoursLabel.setTextFill(Color.WHITE);
		hoursLabel.setStyle("-fx-font-size: 4em;");

		minutesLabel.setText(format(initialMinutes) + " :");
		minutesLabel.setTextFill(Color.WHITE);
		minutesLabel.setStyle("-fx-font-size: 4em;");

		secondsLabel.setText(format(initialSeconds));
		secondsLabel.setTextFill(Color.WHITE);
		secondsLabel.setStyle("-fx-font-size: 4em;");

		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button reset = new Button("Reset");
		
		Button[] buttonArray = {start, stop, reset};
		
		DropShadow d = new DropShadow();
		d.setColor(Color.PALEVIOLETRED);;
		
		for(Button button : buttonArray) {
			button.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
			button.setTextFill(Color.WHITE);
			button.setStyle("-fx-font-size: 1em;");

			button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					start.setEffect(d);
				}
			});
			
			button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					start.setEffect(null);
				}
			});
		}

		start.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				go = true;
				
				if (timeline != null && go == true) {
					timeline.stop();
				}

				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					// KeyFrame event handler
					public void handle(ActionEvent event) {
						if (hours == 0 && minutes == 0 && seconds == 0) {
							timeline.stop();
						}

						// update timerLabel
						hoursLabel.setText(format(hours) + " :");
						minutesLabel.setText(format(minutes) + " :");
						secondsLabel.setText(format(seconds));

						if (hours != 0 && minutes == 0 && seconds == 0) {
							hours--;
							minutes = 60;
						}

						if (seconds == 0) {
							seconds = 60;
							minutes--;
						}

						seconds--;
					}
				}));
				timeline.playFromStart();
			}
		});
		
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				hours = initialHours;
				minutes = initialMinutes;
				seconds = initialSeconds;
			}
			
		});

		// Create and configure HBox
		// gap between components is 20
		HBox timerArea = new HBox(20);
		timerArea.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
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

		buttonsArea.setBackground(
				new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, new Insets(10, 10, 10, 10))));

		buttonsArea.setAlignment(Pos.CENTER);
		buttonsArea.getChildren().addAll(start, stop, reset);

		root.add(buttonsArea, 0, 1);
		
		return root;
	}
	
	public Integer getHours() {
		return hours;
	}
	public Integer getMinutes() {
		return minutes;	
	}
	public Integer getSeconds() {
		return seconds;
	}
	
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
//	public String toString() {
//		return display;
//	}
}
