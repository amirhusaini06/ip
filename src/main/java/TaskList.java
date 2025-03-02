import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with existing tasks.
     *
     * @param tasks ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to delete.
     * @return The deleted Task.
     * @throws XiaoLongException If the index is invalid.
     */
    public Task deleteTask(int index) throws XiaoLongException {
        if (index < 0 || index >= tasks.size()) {
            throw new XiaoLongException("Task " + (index + 1) + " does not exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return The Task at the specified index.
     * @throws XiaoLongException If the index is invalid.
     */
    public Task getTask(int index) throws XiaoLongException {
        if (index < 0 || index >= tasks.size()) {
            throw new XiaoLongException("Task " + (index + 1) + " does not exist.");
        }
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to mark.
     * @return The marked Task.
     * @throws XiaoLongException If the index is invalid.
     */
    public Task markTaskDone(int index) throws XiaoLongException {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     *
     * @param index Index of the task to unmark.
     * @return The unmarked Task.
     * @throws XiaoLongException If the index is invalid.
     */
    public Task markTaskNotDone(int index) throws XiaoLongException {
        Task task = getTask(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index Index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the list.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
