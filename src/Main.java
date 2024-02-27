import Model.Priority;
import Model.Task;
import Model.TaskList;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Task task1 = new Task("Task One", false, LocalDate.now(), Priority.HIGH);
        Task task2 = new Task("Task two", false, LocalDate.now().plusDays(260), Priority.LOW);
        Task task3 = new Task("Task three", true, LocalDate.now().minusMonths(3), Priority.MEDIUM);
        Task task4 = new Task("Task four", false, LocalDate.now().minusYears(2), Priority.HIGH);
        Task task5 = new Task("Task five", true, LocalDate.now().minusYears(1), Priority.LOW);
        Task task6 = new Task("Task six", false, LocalDate.now().plusMonths(2), Priority.MEDIUM);

        TaskList taskList = new TaskList("School tasks", "Tasks that are for my school");

        System.out.println(taskList);

        System.out.println(taskList.totalTasks());

        taskList.addTask(task1, task2, task3, task4, task5, task6);

        System.out.println(taskList.totalTasks());

        System.out.println(taskList.getTasksByPriority(Priority.HIGH));

        System.out.println(taskList.getLateTasks());

        System.out.println(taskList.getComopletedTasks());

        System.out.println(taskList.getUncompletedTasks());

        System.out.println(taskList);

    }
}