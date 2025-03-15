# Schedule-Manager

Overview: 
The Schedule Management System, allows each user to manage his own personal, professional or academic Tasks. It contains two type of tasks, repeated and deadline tasks, each providing different functionalities.

Features:

-	Task scheduling. 
-	Add and Remove and display tasks for a specified week.
-	Task types: Deadline and Repeated. 
-	User Management.
-	Reminders.

Instructions:  

User Class: 

Fundamental component of the Task Management System. It represents a user in the system and manages their personal information, such as username, password, and phone number. Here’s a brief explanation of its key features:
-	username: An Identifier for each user, passes through a username validation process.
-	password: User password, passing through a password validation process to meet specific security reinforcements. 
-	phoneNumber: User phone number, passes through a phone number validation process. 

Task Abstract Class: 

The task class is an abstract class that serves as the foundation for all types of tasks in the Task Management System. It defines the common attributes and behaviors shared by all tasks, such as title, description, start time, and end time. Here’s a brief explanation of its key features: 
-	title: Name of the Task.  
-	description: A brief description.
-	startDateTime: The date and time when the task starts.
-	endDateTime: The date and time when the task ends. 
-	displayTaskDetails(): Display the details of a specified task. 
-	timeUntilStart(): returns the time remaining until the start of the task. 
-	getDuration(): Calculates and returns the duration of the task.
  
RepeatedTask Class extends Task:

The RepeatedTask class is a subclass of the Task class, it represents the tasks that happen repeatedly at a specified frequency.  It extends the functionality of the base Task class by adding attributes and methods specific to repeated tasks. Here’s a brief explanation of its key features:
-	frequency: interval at which the task repeats. 
-	displayTaskDetails(): Display the details of a specified task. 
-	timeUntilStart(): returns the time remaining until the start of the task. 
-	getDuration(): Calculates and returns the duration of the task. 

DeadlineTask Class extends Task: 

The DeadlinTask Class is a subclass of the Task class and represents tasks that have a specific deadline and reminder functionality. It extends the base Task class by adding attributes and methods specific to tasks with deadlines. Here’s a brief explanation of its key features: 
-	reminder: the time before the task starts when a reminder should be sent. 
-	reminderFrequency: The number of reminders to be sent before the task starts.
-	displayTaskDetails(): Display the details of a specified task. 
-	timeUntilStart(): returns the time remaining until the start of the task. 
-	getDuration(): Calculates and returns the duration of the task. 
-	DisplayReminderInterval(): Calculates then returns a string describing the reminder interval. 
-	
TaskManager Class: 

The TaskManager class is the core component of the Task Management System. It manages a list of tasks for a specific user and provides functionalities to add, remove, and display tasks. Here’s a brief explanation of its key features:
- tasks: A list containing all the task, that the user has added. 
- user: The username of the user associated with the task manager. 
- addTask(Task task): Add a specific Task, checks that there is no overlapping and no null values with exception handling.
- removeTask(Task task): Removes a task from the list tasks. 
- displayTasksForWeek(LocalDate StartOfWeek): Displays all tasks in a specific week, while handling both DeadlineTask and RepeatedTask. 
