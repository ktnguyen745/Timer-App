## TimeBlock Timer Application
### _Developed by Kristy Nguyen nguky053 and Matthew Bailey baimy017_

TimeBlock is an application that enables a user to create a schedule of tasks that each last a certain length of time and then run a timer that counts down through each task in the schedule in order. 

This is an example of TimeBlock's graphical user interface.

![An image of the TimeBlock GUI](gui-example.png "The TimeBlock GUI")

### Instructions

#### Adding a Task to the Schedule

To add a task to the schedule, click the *Add Task* button and the following dialog will appear.

![An image of the TimeBlock Task Creation Dialog](newtask-example.png "The TimeBlock new task creation dialog")

Fill out the text fields with your desired values, or leave them blank for default values to be substituted, and then click "ok" to add the task to the schedule.
You may cancel the task creation by exiting the window.

#### Modifying the Schedule

All tasks you create will have a *Swap* and a *Remove* button. 

* The *Swap* button is used to reorder the tasks in the schedule. Clicking the *Swap* buttons of two different tasks will swap their position.

* The *Remove* button is used to remove a task from the schedule. Clicking the *Remove* button will delete the task.

#### Using .csv Files

Instead of adding tasks manually, you may click the *Import .csv* button to import a schedule from a .csv file. This will cause a dialog to appear where you enter the file's name without the .csv extension, which is added automatically. 

* The .csv file you supply must follow a specific format with the task name, hours, minutes and seconds value each being separated by a comma. 

* You can also create a .csv file from the current schedule using the *Create .csv* button. This creates a .csv file in the application directory called "schedule.csv" which matches the required format for the *Import .csv* button.

#### Clearing the Schedule

If you want to reset the schedule and start over, you can click the *Clear Tasks* button to instantly delete all tasks in the schedule.

### Timer Controls

Once you have assembled your schedule, click the *Start* button beneath the timer to start the countdown. It will countdown through every task in the schedule until it reaches the end, where it will stop.

If you need to pause the timer, clicking the *Stop* button will do so. Once you are ready to resume, simply click the *Start* button again.

To restart the timer, click the *Reset* button. 

 
