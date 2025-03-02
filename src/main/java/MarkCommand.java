
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a MarkCommand for the specified task index.
     *
     * @param taskIndex Index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task task = tasks.markTaskDone(taskIndex);
        ui.showTaskMarked(task);
        storage.save(tasks.getTasks());
    }
}
