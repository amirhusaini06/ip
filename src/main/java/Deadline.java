/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline date/time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a completion status.
     * Used when loading from a file.
     *
     * @param description Description of the task.
     * @param by Deadline date/time.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
