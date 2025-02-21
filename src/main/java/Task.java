/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[statusIcon] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
