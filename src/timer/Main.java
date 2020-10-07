package timer;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		Schedule schedule = new Schedule("Test Schedule");
		
		schedule.addTask("Task F", 0, 0, 30);
		
		System.out.println(schedule.tasks());
	}
}
