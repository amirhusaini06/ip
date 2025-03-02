/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param startTime Event start time.
     * @param endTime Event end time.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs an Event task with a completion status.
     * Used when loading from a file.
     *
     * @param description Description of the event.
     * @param timeInfo Time information string (format: "start/end").
     * @param isDone Whether the task is completed.
     */
    public Event(String description, String timeInfo, boolean isDone) {
        super(description, isDone);
        String[] times = timeInfo.split("/");
        this.startTime = times[0];
        this.endTime = times.length > 1 ? times[1] : "";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + "/" + endTime;
    }
}