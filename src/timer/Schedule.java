package timer;

import java.util.ArrayList;

public class Schedule {

	// Instance Variables
	private ArrayList<Task> tasks;
	private String name;
	
	// Constructor
	public Schedule(String name) {
		this.name = name;
		tasks = new ArrayList<Task>();
	}
	
	// AddTask
	public void addTask(String name, Duration duration) {
		Task task = new Task(name, duration);
		tasks.add(task);
	}
	
	// RemoveTask
	public void removeTask() {
		if(tasks.size() > 0) {
			tasks.remove(0);	
		}
	}
	
	// ReorderTasks
	public boolean reorderTasks(int index1, int index2) {
		if(tasks.get(index1) != null && tasks.get(index2) != null) {
			Task tempTask = tasks.get(index2);
			tasks.set(index2, tasks.get(index1));
			tasks.set(index1, tempTask);
			return true; 
		}
		return false;
	}
	
	
	// Getter Methods
	public String name() {
		return name;
	}
	
	public ArrayList<Task> tasks(){
		return tasks;
	}
}
