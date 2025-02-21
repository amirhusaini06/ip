/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param at Event date/time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs an Event task with a completion status.
     * Used when loading from a file.
     *
     * @param description Description of the event.
     * @param at Event date/time.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }
}
