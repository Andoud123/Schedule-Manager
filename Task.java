import java.time.LocalDateTime;
import java.time.LocalTime;
public abstract class Task { 
    static final int DEFAULT_FREQUENCY = 1;
    static final LocalTime DEFAULT_REMINDER= LocalTime.of(00,10);
    protected String title; 
    protected String description; 
    protected LocalDateTime startDateTime; 
    protected LocalDateTime endDateTime;
    
        public Task(String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime ) {
            this.title = title; 
            this.description = description; 
            this.startDateTime = startDateTime; 
            this.endDateTime = endDateTime; 
        }
        public Task(){ 
            this.title = "Untitled"; 
            this.description = "";
            this.startDateTime = LocalDateTime.now();
            this.endDateTime = LocalDateTime.now().plusHours(1);
        }
               
        public Task(Task task){ 
            this(task.title, task.description, task.startDateTime, task.endDateTime);
        }
        public void setTitle(String title){
            this.title = title;
        }
        public void setDescription(String description){ 
            this.description = description;
        }
        public void setStartDateTime(LocalDateTime startDateTime){ 
            this.startDateTime = startDateTime;
        }
        public void setEndDateTime (LocalDateTime endDateTime){
            this.endDateTime = endDateTime;
        }
        public String getTitle(){
		    return title;
	    }
        public String getDescription(){ 
            return description; 
        }
        public LocalDateTime getStartDateTime(){
            return startDateTime;
        }
        public LocalDateTime getEndDateTime(){ 
            return endDateTime;
        }

        public abstract String displayTaskDetails(String title, LocalDateTime startDateTime);
        public abstract String timeUntilStart();
        public abstract String getDuration();
}
