package timer;

import java.util.Timer;
import java.util.TimerTask;

public class Duration {
	// Change to final at some point
	private int secondsDelay = 1000;
	private int secondsPeriod = 1000;

	private int minutes;
	private int seconds;
	private Timer timer = new Timer();

	public Duration(int minutes, int seconds) {
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void run() {
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				System.out.println(getMinutes() + " : " + setInterval());
			}
		}, secondsDelay, secondsPeriod);	
	}

	public int getMinutes() {
		return minutes;
	}
	
	private final int setInterval() {
		if (seconds == 1 && minutes == 0) {
			timer.cancel();
		}

		switch(seconds) {
			case 1:
				--minutes;
				break;
			case 0:	
				seconds = 60;
				break;
		}
		
		return --seconds;
	}
}
