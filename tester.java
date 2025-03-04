import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;


public class tester {
    public static void main(String[] args) {
        User user = new User ("","","");
        TaskManager taskManager = new TaskManager(user.getUsername(), new ArrayList<>()); 
        Scanner scanner = new Scanner(System.in); 
        int nextChoice = 0;
        while (!(nextChoice == 5)){
        nextChoice = 0;
        System.out.println("Welcome to your Schedule Manager!"); 
            System.out.println("Please enter a username");
            String username = scanner.nextLine();
            user = new User(username, "", "");
            while (!user.isValidUsername()){
                System.out.println("Invalid Username.\nUsername must be at least 5 characters long.");
                System.out.println("Please enter a username");
                username = scanner.nextLine();
                user = new User(username, "", "");
            }
            System.out.println("Please enter a password");
            String password = scanner.nextLine();
            user = new User(username, password, "");
                while (!user.isValidPassword()){
                    System.out.println("Invalid password.\nPassword must contain at least one lowercase letter, one uppercase letter, one digit, and be at least 8 characters long.");
                    System.out.println("Please enter a password");
                    password = scanner.nextLine();
                    user = new User(username, password, "");
                }
            System.out.println("Please enter your Lebanese phone number, in this format: +961 __/______");
            String phoneNumber = scanner.nextLine();
            user = new User (username,password,phoneNumber);
                while (!user.isValidPhoneNumber()){
                    System.out.println("Invalid phone number. This is the valid format: +961 __/______");
                    System.out.println("Please enter your phone number");
                    phoneNumber = scanner.nextLine();
                    user = new User (username,password,phoneNumber);
                }
                while (!(nextChoice == 6 || nextChoice == 5)){
                    System.out.println("Please Choose your option");
                    System.out.println("1. Add Task\n2. Remove Task\n3. Display task details\n4. Display tasks for week\n5. Exit\n6. Go Back to home Page");
                    nextChoice =scanner.nextInt();
                    scanner.nextLine();
                        if (nextChoice == 1){ 
                            System.out.println("Please choose between\n1. Deadline task\n2. Repeated task");
                            int taskType = scanner.nextInt();
                            
                            if (taskType == 1) {
                                scanner.nextLine();
                                System.out.println("Choose a Title for the task:");
                                String title = scanner.nextLine();
                                System.out.println("Write a description:");
                                String description = scanner.nextLine();
                                System.out.println("Write the Start Date Time of your task (format: yyyy-MM-dd HH:mm):");
                                String startDateTime = scanner.nextLine();
                                System.out.println("Write the End Date Time of your task (format: yyyy-MM-dd HH:mm):");
                                String endDateTime = scanner.nextLine();
                                System.out.println("Enter the time you want to be reminded prior to your task (format: HH:mm, minimum 10 minutes!):");
                                String reminder = scanner.nextLine();
                                System.out.println("Write the reminder frequency:");
                                int reminderFrequency = scanner.nextInt();
                                scanner.nextLine();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                                    try {
                                        LocalDateTime newStartDateTime = LocalDateTime.parse(startDateTime, formatter);
                                        LocalDateTime newEndDateTime = LocalDateTime.parse(endDateTime, formatter);
                                        LocalTime newReminder = LocalTime.parse(reminder, timeFormatter);
        
                                        DeadlineTask currentDeadlineTask = new DeadlineTask(title, description, newStartDateTime, newEndDateTime, newReminder, reminderFrequency);
                                        System.out.println(taskManager.addTask(currentDeadlineTask));
                                    }
                                    catch (DateTimeParseException ex) {
                                        System.out.println("Error parsing date/time: " + ex.getMessage());
                                    } 
                                    catch (Exception ex) {
                                        System.out.println("An unexpected error occurred: " + ex.getMessage());
                                    }
                            }
                            else if (taskType == 2){
                                scanner.nextLine();
                                System.out.println("Choose a Title for the task");
                                String title = scanner.nextLine();
                                System.out.println("Write a description");
                                String description = scanner.nextLine(); 
                                System.out.println("Write the Start Date Time of your task, (format: yyyy-MM-dd HH:mm)"); 
                                String startDateTime = scanner.nextLine();
                                System.out.println("Write the End Date Time of your task, (format: yyyy-MM-dd HH:mm)");
                                String endDateTime = scanner.nextLine();
                                System.out.println("Enter the frequency of your task");
                                int frequency = scanner.nextInt();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    try {
                                        LocalDateTime newStartDateTime = LocalDateTime.parse(startDateTime, formatter);
                                        LocalDateTime newEndDateTime = LocalDateTime.parse(endDateTime, formatter);
                                        RepeatedTask repeatedTask = new RepeatedTask (title,description,newStartDateTime,newEndDateTime,frequency);
                                        System.out.println(taskManager.addTask(repeatedTask));
                                    } 
                                    catch (DateTimeParseException ex) {
                                        System.out.println("Error parsing date/time: " + ex.getMessage());
                                    } 
                                    catch (Exception ex) {
                                        System.out.println("An unexpected error occurred: " + ex.getMessage());
                                    }
                            }

                                 }
                        if (nextChoice == 2){
                            LocalDateTime newStartDateTime = null;
                            System.out.println("Enter the title of the task to remove: ");
                            String title = scanner.nextLine();
                            System.out.println("Enter the start date and time of the task (yyyy-MM-dd HH:mm): ");
                            String startDateTime = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            try {
                            newStartDateTime = LocalDateTime.parse(startDateTime, formatter);
                            }
                            catch (DateTimeParseException ex) {
                                System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm." + ex.getMessage());
                            }
                
                            String resultMessage = taskManager.removeTask(title, newStartDateTime);
                            System.out.println(resultMessage); 
                        }
                        if (nextChoice == 3){
                            LocalDateTime newStartDateTime = null;
                            System.out.print("Enter the title of the task to display its details: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter the start date and time of the task (yyyy-MM-dd HH:mm): ");
                            String startDateTime = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                try {
                                    newStartDateTime = LocalDateTime.parse(startDateTime, formatter);
                                } 
                                catch (DateTimeParseException ex) {
                                    System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm." + ex.getMessage());
                                }
                            Task foundTask = taskManager.findTask(title, newStartDateTime);
                                if (foundTask != null) {
                                    String taskDetails = foundTask.displayTaskDetails(title, newStartDateTime);
                                    System.out.println(taskDetails);
                                } 
                                else {
                                    System.out.println("Task not found.");
                                }
                        }
                        if (nextChoice == 4){ 
                            System.out.println("Please enter the start date of the week (YYYY-MM-DD): ");
                            String input = scanner.nextLine();
                            LocalDate startOfWeek = LocalDate.parse(input);
                            String tasksForWeek = taskManager.displayTasksForWeek(startOfWeek);
                            System.out.println(tasksForWeek);
                        }   
                    }
                }
        scanner.close();
    }
}   
              
