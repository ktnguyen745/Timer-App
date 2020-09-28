package timer;

public class Task {

	// Instance Variables
	private Duration duration;
	private String name;
	private boolean isComplete;
	
	// Constructors
	public Task(String name, Duration duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public void run() {
		duration.start();
	}
	
	public String toString() {
		return name;
	}
	
	// Getters and Setters
	public Duration duration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public String name() {
		return name;
	}
	public boolean isComplete() {
		return isComplete;
	}
}
