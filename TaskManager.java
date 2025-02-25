import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
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
        public String addTask(Task task) {
            try {
                for (int i=0; i<tasks.size(); i++ ) {
                Task existingTask = tasks.get(i);
                    if (task.getStartDateTime().isBefore(existingTask.getEndDateTime()) &&
                        task.getEndDateTime().isAfter(existingTask.getStartDateTime())) {
                        return "Cannot add task: A task is already scheduled at this time."; 
                    }
                }
                tasks.add(task);
                return "Task added: " + task.getTitle() ; 
            }  catch (NullPointerException e) {
                return "Error: One of the tasks is null. Please check the task details."; 
            }      
        }
        public String removeTask(Task task) {
            if (tasks.remove(task)) {
                return "Task removed: " + task.getTitle();
            } else {
                return "Task not found: " + task.getTitle() ;
            }
        }

 public void displayTasksForWeek(LocalDate startOfWeek) { 
        LocalDate endOfWeek = startOfWeek.plusDays(6); 
        System.out.println("Tasks for " + user + " from " + startOfWeek + " to " + endOfWeek + ":");

        for (int i = 0; i < tasks.size(); i++) {
            Task testTask = tasks.get(i); 
            LocalDateTime startDateTime = testTask.getStartDateTime();
            LocalDateTime endDateTime = testTask.getEndDateTime();

            if ((startDateTime.toLocalDate().isEqual(startOfWeek) || startDateTime.toLocalDate().isAfter(startOfWeek)) &&
                (startDateTime.toLocalDate().isEqual(endOfWeek) || startDateTime.toLocalDate().isBefore(endOfWeek))) {
                if (testTask instanceof DeadlineTask) {
                System.out.println("Title: " + testTask.getTitle() + ", Start: " + startDateTime + ", End: " + endDateTime); 
                }

            }
            if (testTask instanceof RepeatedTask) {
                RepeatedTask repeatedTask = (RepeatedTask) testTask;
                int frequency = repeatedTask.getFrequency();

                LocalDateTime nextOccurrence = startDateTime;

                while (nextOccurrence.toLocalDate().isBefore(startOfWeek)) {
                    nextOccurrence = nextOccurrence.plusDays(frequency);
                }
    
                while ((nextOccurrence.toLocalDate().isBefore(endOfWeek) || nextOccurrence.toLocalDate().isEqual(endOfWeek)) &&
                (nextOccurrence.toLocalDate().isAfter(startOfWeek) || nextOccurrence.toLocalDate().isEqual(startOfWeek))) {
                System.out.println("Title: " + repeatedTask.getTitle() + ", Start: " + nextOccurrence + ", End: " + nextOccurrence.plus(repeatedTask.getDuration()));
                nextOccurrence = nextOccurrence.plusDays(frequency); }
                }
            }
        }
    }
    


