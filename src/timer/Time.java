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
	private Integer hours = 0;
	private Integer minutes = 0;
	private Integer seconds = 0;

	private boolean go = false;
	private int count = 1;

	private String display;
	private Label minutesLabel = new Label();
	private Label hoursLabel = new Label();
	private Label secondsLabel = new Label();
	private Timeline timeline = new Timeline();

	private Schedule sch;

	public Time() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	public Time(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds + 1;
	}

	public Time(Schedule sch) {
		this.sch = sch;
	}

	public void setTimer(Integer hours, Integer minutes, Integer seconds) {
		display = format(hours) + ":" + format(minutes) + ":" + format(seconds);
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

		setTimer(hours, minutes, seconds);

		GridPane root = new GridPane();
		root.setVgap(7);
		Scene scene = new Scene(root, 500, 450);
		hoursLabel.setText(format(hours) + " :");
		hoursLabel.setTextFill(Color.WHITE);
		hoursLabel.setStyle("-fx-font-size: 4em;");
		minutesLabel.setText(format(minutes) + " :");
		minutesLabel.setTextFill(Color.WHITE);
		minutesLabel.setStyle("-fx-font-size: 4em;");
		secondsLabel.setText(format(seconds));
		secondsLabel.setTextFill(Color.WHITE);
		secondsLabel.setStyle("-fx-font-size: 4em;");

		// Create buttons
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button reset = new Button("Reset");

		Button[] buttonArray = { start, stop, reset };

		DropShadow d = new DropShadow();
		d.setColor(Color.PALEVIOLETRED);
		;

		for (Button button : buttonArray) {
			button.setBackground(
					new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
			button.setTextFill(Color.WHITE);
			button.setStyle("-fx-font-size: 1em;");

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
		}

		buttonArray[1].setOpacity(0.65);
		buttonArray[2].setOpacity(0.65);

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				start.setOpacity(0.65);
				buttonArray[1].setOpacity(1);
				buttonArray[2].setOpacity(1);

				if (timeline != null) {
					timeline.stop();
				}

				go = true;

				hours = sch.tasks().get(0).time().hours;
				minutes = sch.tasks().get(0).time().minutes;
				seconds = sch.tasks().get(0).time().seconds;

				timeline = new Timeline();
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					// KeyFrame event handler
					public void handle(ActionEvent event) {
						if (hours == 0 && minutes == 0 && seconds == 0 || go == false) {
							if (sch.tasks().size() == count || go == false) {
								timeline.stop();
							} else {
								hours = sch.tasks().get(count).time().hours;
								minutes = sch.tasks().get(count).time().minutes;
								seconds = sch.tasks().get(count).time().seconds;
								count++;
							}
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

//				go = true;
//				
//				timeline = new Timeline();
//				timeline.setCycleCount(Timeline.INDEFINITE);
//				timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//					// KeyFrame event handler
//					public void handle(ActionEvent event) {
//						if (hours == 0 && minutes == 0 && seconds == 0 || go == false) {
//							timeline.stop();
//						}
//						// update timerLabel
//						hoursLabel.setText(format(hours) + " :");
//						minutesLabel.setText(format(minutes) + " :");
//						secondsLabel.setText(format(seconds));
//						if (hours != 0 && minutes == 0 && seconds == 0) {
//							hours--;
//							minutes = 60;
//						}
//						if (seconds == 0) {
//							seconds = 60;
//							minutes--;
//						}
//						seconds--;
//					}
//				}));
//				timeline.playFromStart();
			}
		});

		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				start.setOpacity(1);
				go = false;
			}
		});

		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				hours = initialHours;
				minutes = initialMinutes;
				seconds = initialSeconds;

				hoursLabel.setText(format(hours) + " :");
				minutesLabel.setText(format(minutes) + " :");
				secondsLabel.setText(format(seconds));
			}

		});

		// Create and configure HBox
		// gap between components is 20
		HBox timerArea = new HBox(20);
		timerArea.setBackground(
				new Background(new BackgroundFill(Color.rgb(212, 117, 83), CornerRadii.EMPTY, Insets.EMPTY)));
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
		buttonsArea.setPadding(new Insets(5, 10, 10, 10));
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

	public String toString() {
		return display;
	}
}