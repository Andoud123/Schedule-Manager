import java.time.Duration;
import java.time.LocalDateTime;

public class RepeatedTask extends Task{ 
    private int frequency;
        public RepeatedTask(String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime,int frequency){ 
            super(title, description, startDateTime, endDateTime);
            setFrequency(frequency);
        }
        public RepeatedTask(){
            super(); 
            this.frequency = 1;
        }
        public RepeatedTask(RepeatedTask repeatedTask){
            super(repeatedTask);
            this.frequency=repeatedTask.frequency;
        }
        public int getFrequency(){
            return frequency;
        }
        public void setFrequency(int frequency){
            if (frequency <= 0)
                frequency = Task.DEFAULT_FREQUENCY;
            this.frequency = frequency;
        }
        public String timeUntilStart(){
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(endDateTime)) { 
                return "The task is finished.";
            }
            else if (now.isAfter(startDateTime)) {
                return "The task has already started.";
            }
            else {
            Duration duration = Duration.between(now, startDateTime);
            long days = duration.toDays();
            long hours = duration.toHours() % 24;
            long minutes = duration.toMinutes() % 60;
            String result = "Time until start: " + days + " days, " + hours + " hours, " + minutes + " minutes";
        return result; }
        }
        public Duration getDuration(){
            return Duration.between(startDateTime, endDateTime);
        }
        public void displayTaskDetails(){
            System.out.println("Repeated Task: " + title);
            System.out.println("Description: " + description);
            System.out.println("Frequency " + frequency);
            System.out.println("Start: " + startDateTime + " | End: " + endDateTime);
            System.out.println("You will be reminded before 30 minutes.");
            System.out.println("The duration of your task is: " + getDuration());
        }
    }

