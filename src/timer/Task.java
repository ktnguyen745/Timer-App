package timer;

public class Task {

	// Instance Variables
	private Time time;
	private String name;
	private boolean isComplete;
	
	// Constructors
	public Task(String name, Time time) {
		this.name = name;
		this.time = time;
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
	public boolean isComplete() {
		return isComplete;
	}
}
