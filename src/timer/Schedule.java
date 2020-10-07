package timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	// Export to CSV file
	public void writeToCSV() throws FileNotFoundException {
		File csvFile = new File("schedule.csv");
		try(PrintWriter writer = new PrintWriter(csvFile)){
			for(Task task : tasks) {
				writer.write(task.toCSV() + "\n");
			}
		}
	}
	
	// Import from CSV file
	public void importCSV(String filename) throws FileNotFoundException {
		try(Scanner fileIn = new Scanner(new File(filename))){
			while(fileIn.hasNextLine()) {
				String[] line = fileIn.nextLine().split(",");
				for(int i = 0; i < line.length; i++) {
					if(line[i] == "" && i == 0) {
						line[i] = "Task " + (i + 1);
					} else if(line[i] == "" && i != 0) {
						line[i] = "0";
					}
				}
				addTask(line[0], new Duration(Integer.parseInt(line[1]), 
						Integer.parseInt(line[2]), Integer.parseInt(line[3])));
			}
			
			
		} catch (FileNotFoundException excpetion) {
			
		}
	}
	
	// Getter Methods
	public String name() {
		return name;
	}
	
	public ArrayList<Task> tasks(){
		return tasks;
	}
}
