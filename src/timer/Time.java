package timer;

/**
 * Time class is responsible for keeping track of hours, minutes and seconds
 */
public class Time {
	protected Integer hours;
	private Integer minutes;
	private Integer seconds;

	// Time default constructor
	public Time() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	// Time constructor
	public Time(Integer hours, Integer minutes, Integer seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	/**
	 * Returns a String representing a component of time in the format of XX.
	 * For example, if 10 was passed in "10" will be returned. If 1 was passing in "01" will be returned.
	 * 
	 * @param integer - takes a component of time
	 * @return a String in the format of XX e.g. if param is 10 then string returned is "10"
	 * 
	 */
	public String format(Integer integer) {
		if (integer < 10) {
			return "0" + integer.toString();
		}
	
		return integer.toString();
	}

	/**
	 * Returns a string containing hours, minutes and seconds in the format of "XX : XX : XX".
	 * If any of the components are not double digits, the function will change put a leading 0 in front 
	 * of the number.
	 * 
	 * @return a String in the format of XX : XX : XX
	 */
	public String toString() {
		return format(hours) + ":" + format(minutes) + ":" + format(seconds);
	}
	
	// Getters and setters 
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

}