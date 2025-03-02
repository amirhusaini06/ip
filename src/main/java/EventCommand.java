
public class EventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    /**
     * Creates an EventCommand with the specified description, start time, and end time.
     *
     * @param description Description of the Event task.
     * @param startTime Event start time.
     * @param endTime Event end time.
     */
    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task task = new Event(description, startTime, endTime);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
