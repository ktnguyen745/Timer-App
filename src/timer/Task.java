package timer;

/**
 * The Task class is responsible for representing a task in the application, storing
 * it's name and the associated Time object. 
 */
public class Task {
	// Instance Variables
	private Time time;
	private String name;
	
	// Constructors
	public Task(String name, Time time) {
		this.name = name;
		this.time = time;
	}
	
	/**
	 * toString method returns the timer's name and time in "Name - HH:MM:SS" format.
	 */
	public String toString() {
		return name + " - " + time.toString();
	}
	
	/**
	 * toCSV method returns the timer's name and time in "Name, hours, minutes, seconds" format.
	 */
	public String toCSV() {
		return name + "," + time.getHours() + "," + time.getMinutes() + "," + time.getSeconds();
	}	
	
	// Getters and Setters
	public Time time() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String name() {
		return name;
	}
}