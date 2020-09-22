package timer;

import java.util.Timer;
import java.util.TimerTask;

public class Duration {
	static int interval = 20;
	static Timer timer = new Timer();
    private int delay = 1000;
    private int period = 1000;

	Duration(){
		timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	            System.out.println(setInterval());

	        }
	    }, delay, period);	
	}

	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
}
