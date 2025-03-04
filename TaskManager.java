import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class TaskManager {
        private String user; 
        private ArrayList<Task> tasks;
            public TaskManager(String user, ArrayList<Task> tasks){
                this.user = user; 
                this.tasks = tasks; 
            }
            public TaskManager () { 
                this.user = "User1234";
                this.tasks = new ArrayList<>(); 
            }
            public TaskManager (TaskManager taskManager){
                this.user = taskManager.user; 
                this.tasks = taskManager.tasks; 
            }
            public String getUser() {
                return user;
            }
            public void setUser(String user) {
                this.user = user;
            }
            public ArrayList<Task> getTasks() {
                return tasks;
            }
            public void setTasks(ArrayList<Task> tasks) {
                this.tasks = tasks;
            }
            public Task findTask(String title, LocalDateTime startDateTime) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                        if (task.getTitle().equalsIgnoreCase(title) && 
                            task.getStartDateTime().equals(startDateTime)) {
                            return task;
                        }
                }
                return null;
            }
            public String addTask(Task task) {
                try {
                    for (int i=0; i<tasks.size(); i++ ) {
                    Task existingTask = tasks.get(i);
                        if (task.getStartDateTime().isBefore(existingTask.getEndDateTime()) &&
                            task.getEndDateTime().isAfter(existingTask.getStartDateTime())) {
                            return "Cannot add task: A task is already scheduled at this time."; 
                        }
                    }
                
                        if (task.getStartDateTime().isAfter(task.getEndDateTime()) || task.getStartDateTime().isBefore(LocalDateTime.now()) || task.getEndDateTime().isBefore(LocalDateTime.now())){
                        return "Cannot add task: StartDateTime/EndDateTime is invalid"; 
                        }
                    
                        else if (!task.getStartDateTime().toLocalDate().isEqual(task.getEndDateTime().toLocalDate())) {
                        return "Error: Start and end times must be on the same day."; 
                        }
                
                        tasks.add(task);
                        return "Task added: " + task.getTitle() ; 
                        }catch (NullPointerException e) {
                        return "Error: One of the tasks is null. Please check the task details."; 
                        }
            }
            public String removeTask(String title, LocalDateTime startDateTime) {
                Task taskToRemove = findTask (title, startDateTime);
                if (taskToRemove != null) {
                    tasks.remove(taskToRemove); 
                    return "Task '" + title + "' removed successfully!";
                }
                return "Task not found!";
            }
            
            public String displayTasksForWeek(LocalDate startOfWeek) { 
                LocalDate endOfWeek = startOfWeek.plusDays(6); 
                String[] daysOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
                StringBuilder output = new StringBuilder();
                output.append(String.format("%-15s %-30s %-30s %-30s%n", "", "Task Title", "Start Date", "End Date"));
                output.append("----------------------------------------------------------------------------------------------------\n");
                ArrayList<ArrayList<Task>> tasksByDay = new ArrayList<>();
        
                    for (int i = 0; i < 7; i++) {
                        tasksByDay.add(new ArrayList<>());
                    }
    
                    for (Task testTask : tasks) {
                        LocalDateTime startDateTime = testTask.getStartDateTime();
                        LocalDateTime endDateTime = testTask.getEndDateTime();
                        LocalDate taskDate = startDateTime.toLocalDate();
        
                            if ((taskDate.isEqual(startOfWeek) || taskDate.isAfter(startOfWeek)) &&
                                (taskDate.isEqual(endOfWeek) || taskDate.isBefore(endOfWeek))) {
                                    int dayIndex = taskDate.getDayOfWeek().getValue() - 1; 
                                    tasksByDay.get(dayIndex).add(testTask);
                            }
                            if (testTask instanceof RepeatedTask) {
                                RepeatedTask repeatedTask = (RepeatedTask) testTask;
                                int frequency = repeatedTask.getFrequency();
                                LocalDateTime nextOccurrence = startDateTime;
                                LocalDateTime nextOccurrenceEnd = endDateTime;



                                while (!nextOccurrence.toLocalDate().isAfter(startOfWeek.minusDays(1))) {
                                    nextOccurrence = nextOccurrence.plusDays(frequency);
                                    nextOccurrenceEnd = nextOccurrenceEnd.plusDays(frequency);
                                }

                                while (!nextOccurrence.toLocalDate().isAfter(endOfWeek)) {
                                    int repeatedDayIndex = nextOccurrence.getDayOfWeek().getValue() - 1;
                                    
                                    if (!tasksByDay.get(repeatedDayIndex).contains(repeatedTask)) {
                                        tasksByDay.get(repeatedDayIndex).add(repeatedTask);
                                    }
                                    
                                    nextOccurrence = nextOccurrence.plusDays(frequency);
                                    nextOccurrenceEnd = nextOccurrenceEnd.plusDays(frequency);
                                }
                                
                            }
                    }
    

                    for (ArrayList<Task> tasksForDay : tasksByDay) {
                        Collections.sort(tasksForDay, Comparator.comparing(Task::getStartDateTime));
                    }
    
    
                    for (int i = 0; i < daysOfWeek.length; i++) {
                        String day = daysOfWeek[i];
                        ArrayList<Task> tasksForDay = tasksByDay.get(i);
        
                        if (tasksForDay.isEmpty()) {
                        output.append(String.format("%-15s %-30s %-30s %-30s%n", day, "No tasks", "", ""));
                        }
                        else {
                            for (Task task : tasksForDay) {
                                String taskTitle = task.getTitle();
                                String startDate;
                                String endDate;

                                if (task instanceof RepeatedTask) {
                                    LocalDateTime nextOccurrence = task.getStartDateTime();
                                    LocalDateTime nextOccurrenceEnd = task.getEndDateTime();
                                    int frequency = ((RepeatedTask) task).getFrequency();

                                    while (!nextOccurrence.toLocalDate().isAfter(endOfWeek)) {
                                        if (nextOccurrence.getDayOfWeek().getValue() - 1 == i) {
                                            break; 
                                        }
                                        nextOccurrence = nextOccurrence.plusDays(frequency);
                                        nextOccurrenceEnd = nextOccurrenceEnd.plusDays(frequency);
                                    }
                    
                                startDate = formatDateTime(nextOccurrence);
                                endDate = formatDateTime(nextOccurrenceEnd); 
                                } 
                                else {
                                    startDate = formatDateTime(task.getStartDateTime());
                                    endDate = formatDateTime(task.getEndDateTime());
                                }
                                output.append(String.format("%-15s %-30s %-30s %-30s%n", day, taskTitle, startDate, endDate));
                            }
                        }
                    }
    
                return output.toString(); 
            }
            
            public String formatDateTime(LocalDateTime dateTime) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return dateTime.format(formatter);
            }
        } 
    
