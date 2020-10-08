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

/**
 * The CountdownTimer class is responsible for altering the Time given to it by the
 * Schedule object and displaying that to the user
 */
public class CountdownTimer {
	private Timeline timeline;
	private Time countdownTimer = new Time();
	private Schedule schedule; // The schedule users will be inputting into
	private int count = 0; // Counts the number of arrayList objects in schedule, starting at 1
	private boolean go = false; // Stops the count down if it is set to false
	private boolean restart = false; // If true, indicates that the timer will be restarting
	
	// Constructor
	public CountdownTimer(Schedule schedule) {
		this.schedule = schedule;
	}

	/**
	 * Determines what the timer should be set to based off the list of tasks in the
	 * Schedule object. Displays graphics accordingly
	 * 
	 * @return a GridPane with all the GUI components for the timer
	 */
	public GridPane getTimer() {
		GridPane root = new GridPane();
		root.setVgap(7); // Set the vertical gap between elements in root

		// Create the display for hours, minutes and seconds. Set the display to 00 : 00
		// : 00.
		Label hoursLabel = new Label((countdownTimer.format(countdownTimer.getMinutes())) + " :");
		Label minutesLabel = new Label(countdownTimer.format(countdownTimer.getHours()) + " :");
		Label secondsLabel = new Label(countdownTimer.format(countdownTimer.getSeconds()));

		Label[] labelsArr = { hoursLabel, minutesLabel, secondsLabel };

		// Set the font and size of the time display
		for (Label label : labelsArr) {
			label.setTextFill(Color.WHITE);
			label.setStyle("-fx-font-size: 4em;");
		}

		// Create the start, stop and reset button. Label the buttons accordingly.
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button reset = new Button("Reset");

		// Create and set a shadow effect to blue
		DropShadow d = new DropShadow();
		d.setColor(Color.GREY);

		Button[] buttonArray = { start, stop, reset };

		for (Button button : buttonArray) {
			// Set the all the buttons to the same button colour, text colour and text size.
			// Color.rgb(208, 135, 200)
			button.setBackground(
					new Background(new BackgroundFill(Color.MEDIUMVIOLETRED, new CornerRadii(10), Insets.EMPTY)));
			button.setTextFill(Color.WHITE);
			button.setStyle("-fx-font-size: 1em;");

			// When a mouse hovers over a button, a shadow will appear around the button.
			button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button.setEffect(d);
				}
			});

			// When a mouse exits the button space the shadow will disappear.
			button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					button.setEffect(null);
				}
			});
		}

		// Initially, fade out the stop and reset button to indicate they can't be used
		// yet
		stop.setOpacity(0.65);
		reset.setOpacity(0.65);
		
		// Create a display for the task name so users know what task is being counted down
		Label taskName = new Label();
		taskName.setStyle("-fx-font-size: 1.25em;");
		taskName.setTextFill(Color.WHITE);	

		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// Start the countdown timer when the start button is pressed and there are
				// tasks inside schedule.
				if (schedule.tasks().size() > 0) {
					// Change the opacity of buttons to indicate which buttons can be used
					start.setOpacity(0.65);
					stop.setOpacity(1);
					reset.setOpacity(1);

					// Set go to true to indicate the countdown timer should start
					go = true;

					timeline = new Timeline();
					timeline.setCycleCount(Timeline.INDEFINITE);
					timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
						// KeyFrame event handler
						public void handle(ActionEvent event) {
							if (go == false) {
								start.setOpacity(1);
								stop.setOpacity(0.65);
								timeline.stop();
							}
							
							if (countdownTimer.getHours() == 0 && countdownTimer.getMinutes() == 0
									&& countdownTimer.getSeconds() == 0 || restart == true) {
								// Stop the timer if go is false or each Task in Schedule has been counted down
								if (schedule.tasks().size() <= count) {
									start.setOpacity(1);
									stop.setOpacity(0.65);
									reset.setOpacity(0.65);
									
									count = 0; // Reset count
									restart = true; // Change to true to indicate the timer will be restarting.
																		
									timeline.stop();
									// Otherwise, set timer to the next task in the Schedule
								} else {
									System.out.println(schedule.tasks().toString());
									
									countdownTimer.setHours(schedule.tasks().get(count).time().getHours());
									countdownTimer.setMinutes(schedule.tasks().get(count).time().getMinutes());
									countdownTimer.setSeconds(schedule.tasks().get(count).time().getSeconds());
									
									// Set the task name to the current task
									taskName.setText(schedule.tasks().get(count).name());
									
									restart = false; 
									
									count++;
								}
							}

							// Update the time display
							hoursLabel.setText(countdownTimer.format(countdownTimer.getHours()) + " :");
							minutesLabel.setText(countdownTimer.format(countdownTimer.getMinutes()) + " :");
							secondsLabel.setText(countdownTimer.format(countdownTimer.getSeconds()));

							// Hours is not 0 but minutes is 0
							if (countdownTimer.getHours() != 0 && countdownTimer.getMinutes() == 0
									&& countdownTimer.getSeconds() == 0) {
								// Decrease hours by 1
								countdownTimer.setHours(countdownTimer.getHours() - 1);
								// Change minutes to 60
								countdownTimer.setMinutes(60);
							}

							// If seconds is 0
							if (countdownTimer.getSeconds() == 0) {
								// Change seconds to 60
								countdownTimer.setSeconds(60);
								// Decrease minutes by 1
								countdownTimer.setMinutes(countdownTimer.getMinutes() - 1);
							}

							// Decrease seconds by 1
							countdownTimer.setSeconds(countdownTimer.getSeconds() - 1);
						}
					}));

					// Start countdown
					timeline.playFromStart();
				}
			}
		});

		// When the stop button is pressed change the value of go to false. This will
		// stop the timer.
		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				start.setOpacity(1);
				stop.setOpacity(0.65);
				go = false;
			}
		});

		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// When the reset button is pressed the time will be changed back to the
				// original time
				countdownTimer.setHours(schedule.tasks().get(count).time().getHours());
				countdownTimer.setMinutes(schedule.tasks().get(count).time().getMinutes());
				countdownTimer.setSeconds(schedule.tasks().get(count).time().getSeconds());

				// The change will be displayed immediately
				hoursLabel.setText(countdownTimer.format(countdownTimer.getHours()) + " :");
				minutesLabel.setText(countdownTimer.format(countdownTimer.getMinutes()) + " :");
				secondsLabel.setText(countdownTimer.format(countdownTimer.getSeconds()));
			}
		});

		// Create a HBox to store the displays for hours, minutes and seconds
		HBox timerArea = new HBox(20);
		timerArea.getChildren().addAll(hoursLabel, minutesLabel, secondsLabel);


		// Create a HBox to store the buttons. All elements inside of this HBox is centred.
		HBox buttonsArea = new HBox(10);
		buttonsArea.getChildren().addAll(start, stop, reset);
		buttonsArea.setAlignment(Pos.CENTER);	

		HBox taskNameArea = new HBox();
		taskNameArea.getChildren().addAll(taskName);
		taskNameArea.setAlignment(Pos.CENTER);
		
		// Add both HBoxes to the root component
		root.add(taskNameArea, 0, 0);
		root.add(timerArea, 0, 1);
		root.add(buttonsArea, 0, 2);

		return root;
	}
}
