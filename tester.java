import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;

public class tester {
    public static void main(String[] args) {
        User user = new User("USER1","name","0234");
        TaskManager taskManager = new TaskManager(user.getUsername(), new ArrayList<>());
        RepeatedTask task1 = new RepeatedTask("OOD", "Object Oriented Design", LocalDateTime.of(2025,01,8,10,0,0),LocalDateTime.of(2025,01,8,11,0,0),3);
        DeadlineTask task2 = new DeadlineTask("OOP", "Object Oriented Programming", LocalDateTime.of(2026,01,13,10,0,0), LocalDateTime.of(2026,01,14,10,0,0),LocalTime.of(1, 0),2);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.displayTasksForWeek(LocalDate.of(2025,01,9));  
        task2.displayTaskDetails();
        task1.displayTaskDetails();
        System.out.println(task2.timeUntilStart());
        taskManager.removeTask(task1);
        taskManager.addTask(null);
    }
}
