package timer;

import java.util.Timer;
import java.util.TimerTask;

public class Duration {
	// Change to final at some point
	private int secondsDelay = 1000;
	private int secondsPeriod = 1000;

	private int hours;
	private int minutes;
	private int seconds;
	
	private Timer timer = new Timer();

	public Duration(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds + 1;
	}
	
	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			
			public void run() {
				System.out.println(getHours() + " : " + getMinutes() 
				+ " : " + setInterval());
			}
		}, secondsDelay, secondsPeriod);	
	}

	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	private final int setInterval() {
		if (hours == 0 && minutes == 0 && seconds == 1) {
			timer.cancel();
		}

		switch(seconds) {
			case 1:
				if(hours != 0 && minutes == 0) {
					--hours;
					minutes = 59;
				}
				else {
					--minutes;
				}
				
				break;
			case 0:	
				seconds = 60;
				break;
		}
				
		return --seconds;
	}
}
