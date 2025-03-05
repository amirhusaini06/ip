/**
 * Command to add a ToDo task.
 */

public class ToDoCommand extends Command {
    private String description;

    /**
     * Creates a ToDoCommand with the specified description.
     *
     * @param description Description of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws XiaoLongException {
        Task task = new ToDo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
