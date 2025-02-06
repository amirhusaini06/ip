import java.util.Scanner;

/**
 * A chatbot program that tracks different types of tasks.
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
        System.out.println(DESIGN);
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("I can manage tasks for you! Say 'todo', 'deadline', or 'event' to add a task. Say 'bye' to exit.");
        System.out.println("___________________________________________________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
                break;
            }else if (input.equalsIgnoreCase("list")) {
                displayTasks();
            }else if (input.startsWith("mark")) {
                handleMarkCommand(input);
            }else if (input.startsWith("unmark")) {
                handleUnmarkCommand(input);
            }else if (input.startsWith("todo")) {
                handleToDoCommand(input);
            }else if (input.startsWith("deadline")) {
                handleDeadlineCommand(input);
            }else if (input.startsWith("event")) {
                handleEventCommand(input);
            }else {
                System.out.println("Invalid command! Try 'todo', 'deadline', 'event', 'list', 'mark', or 'unmark'.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the list of tasks.
     */
    private void displayTasks() {
        if (taskCount == 0) {
            System.out.println("List is empty. Add a task using 'todo', 'deadline', or 'event'.");
        }else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    /**
     * Handles marking a task as done.
     */
    private void handleMarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber >= 1 && taskNumber <= taskCount) {
                tasks[taskNumber - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
            }else {
                System.out.println("Invalid task number.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid task number to mark.");
        }
    }

    /**
     * Handles unmarking a task as not done.
     */
    private void handleUnmarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber >= 1 && taskNumber <= taskCount) {
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber - 1]);
            }else {
                System.out.println("Invalid task number.");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid task number to unmark.");
        }
    }
    /**
     * Handles adding a ToDo task.
     */
    private void handleToDoCommand(String input) {
        try {
            String description = input.substring(5).trim();
            tasks[taskCount] = new ToDo(description);
            taskCount++;
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
        } catch (Exception e) {
            System.out.println("Invalid ToDo format! Use: todo [task description]");
        }
    }

    /**
     * Handles adding a Deadline task.
     */
    private void handleDeadlineCommand(String input) {
        try {
            String[] parts = input.substring(9).split(" /by ");
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            taskCount++;
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
        } catch (Exception e) {
            System.out.println("Invalid Deadline format! Use: deadline [task] /by [time]");
        }
    }

    /**
     * Handles adding an Event task.
     */
    private void handleEventCommand(String input) {
        try {
            String[] parts = input.substring(6).split(" /from | /to ");
            tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
            taskCount++;
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
        } catch (Exception e) {
            System.out.println("Invalid Event format! Use: event [task] /from [start] /to [end]");
        }
    }

    public static void main(String[] args) {
        XiaoLong xiaoLong = new XiaoLong();
        xiaoLong.start();
    }
}
