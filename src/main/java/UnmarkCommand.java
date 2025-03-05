/**
 * Command to mark a task as not done.
 */

public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates an UnmarkCommand for the specified task index.
     *
     * @param taskIndex Index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task task = tasks.markTaskNotDone(taskIndex);
        ui.showTaskUnmarked(task);
        storage.save(tasks.getTasks());
    }
}
