import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeadlineTask extends Task {
    private LocalTime reminder;
    private int reminderFrequency;
    public DeadlineTask(String title, String description, LocalDateTime startDateTime, LocalDateTime endDateTime,LocalTime reminder, int reminderFrequency){ 
        super(title, description, startDateTime, endDateTime);
        setReminder(reminder);
        setReminderFrequency(reminderFrequency);
    }
    public DeadlineTask(){
        super(); 
        this.reminder = LocalTime.of(0, 30);
        this.reminderFrequency = 1;
    }
    public DeadlineTask(DeadlineTask deadlineTask){
        super(deadlineTask);
        this.reminder=deadlineTask.reminder;
        this.reminderFrequency=deadlineTask.reminderFrequency;
    }
    public LocalTime getReminder() {
        return reminder;
    }
    public void setReminder(LocalTime reminder) {
        if (reminder.isBefore(LocalTime.of(00,10))){
            this.reminder = DEFAULT_REMINDER;
        }
        else{
        this.reminder = reminder;
        }
    }
    public int getReminderFrequency() {
        return reminderFrequency;
    }
    public void setReminderFrequency(int reminderFrequency) {
        if (reminderFrequency <= 0) { 
            this.reminderFrequency = DEFAULT_FREQUENCY; 
        }
        else {
        this.reminderFrequency = reminderFrequency; }
    }
    public int convertToMinutes(){
        return reminder.getHour() * 60 + reminder.getMinute();
    }
    public String displayReminderInterval() {
        int min = convertToMinutes();
            return "You will receive " + reminderFrequency + " reminder(s), each scheduled " + (min/reminderFrequency) + " minutes prior to the start of your task.";
        } 
    public String timeUntilStart() {
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
    public Duration getDuration() {
        return Duration.between(startDateTime, endDateTime);
    }
    public void displayTaskDetails() {
        System.out.println("Task Details");
        System.out.println("Deadline Task: " + title);
        System.out.println("Description: " + description);
        System.out.println(displayReminderInterval());
        System.out.println("Start: " + startDateTime + " | End: " + endDateTime);
        System.out.println("The duration of your task is: " + getDuration());
    }
}
