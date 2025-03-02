import java.util.Scanner;


public class Ui {
    private static final String DESIGN =
            " ___    ___ ___  ________  ________  ___       ________  ________   ________     \n" +
                    "|\\  \\  /  /|\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ___  \\|\\   ____\\    \n" +
                    "\\ \\  \\/  / | \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\___|    \n" +
                    " \\ \\    / / \\ \\  \\ \\   __  \\ \\  \\\\  \\ \\  \\    \\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\  ___  \n" +
                    "  /     \\/   \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\ \\  \\____\\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \n" +
                    " /  /\\   \\    \\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\\n" +
                    "/__/ /\\ __\\    \\|__|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|__| \\|__|\\|_______|\n" +
                    "|__|/ \\|__|                                                                       ";

    private static final String DIVIDER = "________________________________________________";
    private Scanner scanner;

    /**
     * Creates a new UI object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        System.out.println(DESIGN);
        System.out.println("Welcome to XiaoLong chatbot!");
        System.out.println("Type 'todo', 'deadline', 'event', 'delete', 'list', or 'bye'.");
        showLine();
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Reads user input.
     *
     * @return The user's command.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays a message about tasks not being loaded properly.
     */
    public void showLoadingError() {
        System.out.println("Could not load tasks from file. Starting with an empty task list.");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! So long from XiaoLong.");
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The task list to display.
     */
    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List is empty. Add a task using 'todo', 'deadline', or 'event'.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays a message that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Displays a message that a task has been added.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Displays tasks that match a keyword.
     *
     * @param tasks The task list to search through.
     * @param keyword The keyword to search for.
     */
    public void showMatchingTasks(TaskList tasks, String keyword) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");

        boolean hasMatches = false;
        int count = 1;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("     " + count + "." + task);
                count++;
                hasMatches = true;
            }
        }

        if (!hasMatches) {
            System.out.println("     No matching tasks found.");
        }

        System.out.println("    ____________________________________________________________");
    }
}
