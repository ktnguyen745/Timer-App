package timer;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		
		Schedule schedule = new Schedule("Test Schedule");
		
		schedule.addTask("Task A", new Duration(1, 10, 30));
		schedule.addTask("Task B", new Duration(0, 20, 0));
		schedule.addTask("Task C", new Duration(0, 45, 15));
		schedule.addTask("Task D", new Duration(2, 0, 22));
		
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
