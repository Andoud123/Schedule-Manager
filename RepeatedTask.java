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
            String result = days + " days, " + hours + " hours, " + minutes + " minutes";
            return result; 
            }
        }
        public String getDuration() {
            Duration duration = Duration.between(startDateTime, endDateTime);
            
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60; 
            long seconds = duration.getSeconds() % 60; 
        
            return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
        }
        public String displayTaskDetails(String title, LocalDateTime startDateTime) {
            return "Repeated Task: " + title + "\n"
                 + "Description: " + description + "\n"
                 + "Frequency: " + frequency + "\n"
                 + "Start: " + startDateTime + " | End: " + endDateTime + "\n"
                 + "You will be reminded before 30 minutes.\n"
                 + "The duration of your task is: " + getDuration()+ "\n"
                 + "Your task will start in: " + timeUntilStart();
        }
        
    }

