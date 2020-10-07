package timer;

public class Time {
	private Integer hours;
	private Integer minutes;
	private Integer seconds;

	public Time() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	public Time(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
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

	private String format(Integer integer) {
		if (integer < 10) {
			return "0" + integer.toString();
		}
		return integer.toString();
	}
	
	public String toString() {
		return format(hours) + ":" + format(minutes) + ":" + format(seconds);
	}
}