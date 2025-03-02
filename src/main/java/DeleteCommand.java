
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand for the specified task index.
     *
     * @param taskIndex Index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
