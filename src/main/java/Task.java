/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

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
     * Constructs a Task with a description and completion status.
     * Used when loading from a file.
     *
     * @param description The task description.
     * @param isDone Whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
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

    /**
     * Converts the task to a savable file format.
     * @return String representation of the task.
     */
    public String toFileFormat() {
        return (this instanceof ToDo ? "T" :
                (this instanceof Deadline ? "D" : "E")) + " | " +
                (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Creates a Task object from a formatted file line.
     * @param line A line from the save file.
     * @return A Task object.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Skip invalid lines
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
                case "T":
                    return new ToDo(description, isDone);
                case "D":
                    if (parts.length > 3) {
                        return new Deadline(description, parts[3], isDone);
                    }
                    return null; // Invalid deadline format
                case "E":
                    if (parts.length > 3) {
                        return new Event(description, parts[3], isDone);
                    }
                    return null; // Invalid event format
                default:
                    return null; // Skip unknown task types
            }
        } catch (Exception e) {
            return null; // Skip any tasks that cause errors
        }
    }

}
