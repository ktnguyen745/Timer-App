package timer;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		Schedule schedule = new Schedule("Test Schedule");
		schedule.addTask("Task A", 0, 1, 2);
		schedule.addTask("Task B", 0, 0, 32);
		schedule.addTask("Task C", 0, 2, 40);
		schedule.runSchedule();
		
		try {
			schedule.writeToCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
