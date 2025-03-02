
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Creates a DeadlineCommand with the specified description and deadline.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline date/time.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
