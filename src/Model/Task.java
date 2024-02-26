package Model;

import java.time.LocalDate;

public class Task {

    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private  Priority priority;


    public Task() {
        this.description = "Null";
        this.completed = false;
        this.dueDate = LocalDate.now();
        this.priority = Priority.LOW;
    }

    public Task(String description, boolean completed, LocalDate dueDate, Priority priority) {
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return String.format("Model.Task[Description = %s, Completed = %s, DueDate = %s, Model.Priority = %s]", description,
                completed,
                dueDate,
                priority);
    }

}
