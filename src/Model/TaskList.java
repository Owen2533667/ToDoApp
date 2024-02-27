package Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private String tasksCollectionTitle;
    private String tasksDescription;
    private final LinkedList<Task> taskLinkedList;


    public TaskList() {
        this.tasksCollectionTitle = "Default Tasks";
        this.tasksDescription = "Default Description";
        this.taskLinkedList = new LinkedList<>();
    }

    public TaskList(String tasksCollectionTitle) {
        this.tasksCollectionTitle = tasksCollectionTitle;
        this.tasksDescription = "Default Description";
        this.taskLinkedList = new LinkedList<>();
    }

    public TaskList(String tasksCollectionTitle, String tasksDescription) {
        this.tasksCollectionTitle = tasksCollectionTitle;
        this.tasksDescription = tasksDescription;
        this.taskLinkedList = new LinkedList<>();
    }

    public TaskList(String tasksCollectionTitle, String tasksDescription, LinkedList<Task> taskLinkedList) {
        this.tasksCollectionTitle = tasksCollectionTitle;
        this.tasksDescription = tasksDescription;
        this.taskLinkedList = taskLinkedList;
    }

    public String getTasksCollectionName() {
        return tasksCollectionTitle;
    }

    public void updateCollectionTitle(String title) {
        this.tasksCollectionTitle = title;
    }


    public String getTasksDescription() {
        return tasksDescription;
    }

    public void updateTasksDescription(String tasksDescription) {
        this.tasksDescription = tasksDescription;
    }

    public void addTask(Task... task) {
        taskLinkedList.addAll(List.of(task));
    }

    public void removeTask(Task task) {
        taskLinkedList.remove(task);
    }

    public void removeAllTasks() {
        taskLinkedList.clear();
    }

    public int totalTasks() {
        return taskLinkedList.size();
    }

    public LinkedList<Task> getTasks() {
        return taskLinkedList;
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskLinkedList.stream().filter(task -> task.getPriority() == priority).toList();
    }

    public List<Task> getComopletedTasks() {
        return  taskLinkedList.stream().filter(Task::isCompleted).toList();
    }

    public List<Task> getUncompletedTasks() {
        return  taskLinkedList.stream().filter(task -> !task.isCompleted()).toList();
    }

    public List<Task> getLateTasks() {
        return taskLinkedList.stream().filter(task -> task.getDueDate().isAfter(LocalDate.now()) && !task.isCompleted()).toList();
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "Title='" + tasksCollectionTitle + '\'' +
                ", Description='" + tasksDescription + '\'' +
                ", Tasks=" + taskLinkedList +
                '}';
    }
}
