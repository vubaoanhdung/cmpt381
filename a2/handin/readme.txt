Author: Damon(Bao) Vu
Student #: 11261393
NSID: bav965

README File

To start the Application
	- Unzip the part3 zip file in Part3 directory
	- *** Please run the TTrack.java (NOT SummaryWindow.java)

Organization of the handin

	Part1
		part1a-taskDescription.pdf
		part1b-sketches
			task1
				images
			task2
				images
			task3
				images
		part1c-walkthrough.pdf
	
	Part2
		part2a.pdf
	Part3
		source code zip file
		
NOTE: 

- If you want to make the timer run a little bit better for testing purposes, 
then please change row 40 in Record.java to:

private void startTimer() {
        this.timer.schedule(this.timerTask,0,1000);
    }

=> This will update the duration column every second (not every minute as the 
original!)

- The Application serve every purpose of the task descriptions very well.
However, there still are limitations that I discovered during the building 
process.
	- If you want to delete a record the first time, the window size 
	of the application will collapsed. But it will be back to normal
	once you confirm your choice on the confimation pop-up dialog. This
	behavior will NOT happen the next time you try to delete another 
	record. Moreover, if you resize the window (even a small bit) 
	before the first time deletion, then the behavior above will 
	also NOT occur. I think this is a bug of JavaFX or Intellij as this
	behavior only occurs on Linux System (specifically, Ubuntu 20.04). 
	It works just fine on Window System, surprisingly! 
	(I have tested the application on both OS Systems)
	
	- At the current state of the application, it is hard to tell
	which records have been terminated/stopped. In addition, the stop
	button should be disabled once the record has been stopped.
	
- What's next (What I could do to improve the application)
	- setRowFactory for the overview table and some css to change the 
	background-color of the stopped record row, and disable the stop 
	button if the user choose a stopped record row. By doing so, it will
	be easier for the user to know which records have been terminated.
	


