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
	private Duration total;
	
	// Constructor
	public Schedule(String name) {
		this.name = name;
		tasks = new ArrayList<Task>();
	}
	
	// runSchedule
	public void runSchedule() {
//		total.start();
		for(Task task : tasks) {
			task.run();
			try {
				Thread.sleep((task.duration().getHours() * 3600000) + 
						(task.duration().getMinutes() * 60000) +
						(task.duration().getSeconds() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Task Complete");
		}
		System.out.println("Schedule Complete");
	}
	
	// AddTask
	public void addTask(String name, int hours, int minutes, int seconds) {
		Task task = new Task(name, new Duration(hours, minutes, seconds));
		tasks.add(task);
		if (total != null) {
			int totalHours = hours + total.getHours();
			int totalMinutes = minutes + total.getMinutes();
			if(totalMinutes >= 60) {
				totalHours += (int) Math.floor(totalMinutes / 60);
				totalMinutes = totalMinutes % 60; 
			}
			int totalSeconds = seconds + total.getSeconds();
			if(totalSeconds >= 60) {
				totalMinutes += (int) Math.floor(totalSeconds / 60);
				totalSeconds = totalSeconds % 60;
			}
			total = new Duration(totalHours, totalMinutes, totalSeconds);
		} else {
			total = new Duration(hours, minutes, seconds);
		}
	}
	
	// RemoveTask
	public void removeTask(int index) {
		if(tasks.size() >= index) {
			int totalHours = total.getHours() - tasks.get(index).duration().getHours();
			int totalMinutes = total.getMinutes() - tasks.get(index).duration().getMinutes();
			if(totalMinutes < 0) {
				totalHours--;
				totalMinutes += 60;
			}
			int totalSeconds = total.getSeconds() - tasks.get(index).duration().getSeconds();
			if(totalSeconds < 0) {
				totalMinutes--;
				totalSeconds += 60;
			}
			total = new Duration(totalHours, totalMinutes, totalSeconds);
			tasks.remove(index);	
		} 
		if(tasks.size() == 0) {
			total = new Duration(0, 0, 0);
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
				addTask(line[0], Integer.parseInt(line[1]), 
						Integer.parseInt(line[2]), Integer.parseInt(line[3]));
				addTask(line[0], Integer.parseInt(line[1]), 
						Integer.parseInt(line[2]), Integer.parseInt(line[3]));
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
	
	public Duration total() {
		return total;
	}
}
