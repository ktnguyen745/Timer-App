package timer;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		
		Schedule schedule = new Schedule("Test Schedule");
		
		schedule.addTask("Task A", null);
		schedule.addTask("Task B", null);
		schedule.addTask("Task C", null);
		schedule.addTask("Task D", null);
		
		System.out.println(schedule.name() + " is currently: " +  schedule.tasks());
		
		schedule.removeTask();
		
		System.out.println("After removal, " + schedule.name() + " is currently: " +  schedule.tasks());
		
		schedule.reorderTasks(0, 2);
		
		System.out.println("After swapping, " + schedule.name() + " is currently: " +  schedule.tasks());
		
		try {
			schedule.writeToCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
