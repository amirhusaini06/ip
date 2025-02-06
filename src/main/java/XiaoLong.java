import java.util.Scanner;

/**
 * A chatbot program that manages a list of tasks.
 */
public class XiaoLong {
    private static final int MAX_TASKS = 100;
    private static final String DESIGN =
            " ___    ___ ___  ________  ________  ___       ________  ________   ________     \n" +
                    "|\\  \\  /  /|\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ___  \\|\\   ____\\    \n" +
                    "\\ \\  \\/  / | \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\___|    \n" +
                    " \\ \\    / / \\ \\  \\ \\   __  \\ \\  \\\\  \\ \\  \\    \\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\  ___  \n" +
                    "  /     \\/   \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\ \\  \\____\\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \n" +
                    " /  /\\   \\    \\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\\n" +
                    "/__/ /\\ __\\    \\|__|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|__| \\|__|\\|_______|\n" +
                    "|__|/ \\|__|                                                                       ";

    private Task[] tasks;
    private int taskCount;

    /**
     * Constructs a XiaoLong chatbot instance.
     */
    public XiaoLong() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    /**
     * Starts the XiaoLong chatbot.
     */
    public void start() {
        System.out.println("___________________________________________________________________________________________________");
        System.out.println(DESIGN);
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("What can I do for you today? I can make a list for you. Just input anything. Say bye to exit!");
        System.out.println("___________________________________________________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (input.startsWith("mark")) {
                handleMarkCommand(input);
            } else if (input.startsWith("unmark")) {
                handleUnmarkCommand(input);
            } else {
                addTask(input);
            }
        }

        scanner.close();
    }

    /**
     * Displays the list of tasks.
     */
    private void displayTasks() {
        if (taskCount == 0) {
            System.out.println("List is empty. Please put any other input to add something, or say bye to exit!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
            System.out.println("Input mark/unmark N to mark/unmark the Nth item on your list");
        }
    }

    /**
     * Handles the 'mark' command to mark a task as done.
     *
     * @param input The user input containing the 'mark' command and task number.
     */
    private void handleMarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber >= 1 && taskNumber <= taskCount) {
                tasks[taskNumber - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
            } else {
                System.out.println("Invalid task number. Please enter a valid number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number to mark.");
        }
    }

    /**
     * Handles the 'unmark' command to mark a task as not done.
     *
     * @param input The user input containing the 'unmark' command and task number.
     */
    private void handleUnmarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber >= 1 && taskNumber <= taskCount) {
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber - 1]);
            } else {
                System.out.println("Invalid task number. Please enter a valid number.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number to unmark.");
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param description The description of the task to add.
     */
    private void addTask(String description) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(description);
            taskCount++;
            System.out.println("Added to list: " + description);
            System.out.println("Say list to show list, or say bye to exit!");
        } else {
            System.out.println("Task list is full. Say list to show list, or say bye to exit!");
        }
    }

    /**
     * The main method to run the XiaoLong chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        XiaoLong xiaoLong = new XiaoLong();
        xiaoLong.start();
    }
}
