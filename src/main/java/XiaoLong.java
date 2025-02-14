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

    public XiaoLong() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    public void start() {
        System.out.println(DESIGN);
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("I can manage tasks for you! Say 'todo', 'deadline', or 'event' to add a task. Say 'bye' to exit.");
        System.out.println("___________________________________________________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            try {
                input = scanner.nextLine().trim();
                processCommand(input);
            } catch (XiaoLongException e) {
                System.out.println("____________________________________________________________");
                System.out.println("  OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

    private void processCommand(String input) throws XiaoLongException {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            displayTasks();
        } else if (input.startsWith("mark")) {
            handleMarkCommand(input);
        } else if (input.startsWith("unmark")) {
            handleUnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            handleToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            handleDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            handleEventCommand(input);
        } else {
            throw new XiaoLongException("I'm sorry, but I don't understand what that means.");
        }
    }

    private void displayTasks() {
        if (taskCount == 0) {
            System.out.println("List is empty. Add a task using 'todo', 'deadline', or 'event'.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    private void handleMarkCommand(String input) throws XiaoLongException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new XiaoLongException("Invalid task number. Enter a number between 1 and " + taskCount + ".");
            }
            tasks[taskNumber - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
        } catch (Exception e) {
            throw new XiaoLongException("Please enter a valid task number to mark.");
        }
    }

    private void handleUnmarkCommand(String input) throws XiaoLongException {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            if (taskNumber < 1 || taskNumber > taskCount) {
                throw new XiaoLongException("Invalid task number. Enter a number between 1 and " + taskCount + ".");
            }
            tasks[taskNumber - 1].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber - 1]);
        } catch (Exception e) {
            throw new XiaoLongException("Please enter a valid task number to unmark.");
        }
    }

    private void handleToDoCommand(String input) throws XiaoLongException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new XiaoLongException("The description of a 'todo' cannot be empty. Try again.");
        }
        tasks[taskCount] = new ToDo(description);
        taskCount++;
        System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
    }

    private void handleDeadlineCommand(String input) throws XiaoLongException {
        try {
            String[] parts = input.substring(8).split(" /by ", 2);
            if (parts.length < 2) {
                throw new XiaoLongException("Invalid format! Use: deadline [task] /by [time]");
            }
            tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
            taskCount++;
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
        } catch (IndexOutOfBoundsException e) {
            throw new XiaoLongException("Invalid format! Use: deadline [task] /by [time]");
        }
    }

    private void handleEventCommand(String input) throws XiaoLongException {
        try {
            String[] parts = input.substring(5).split(" /from | /to ", 3);
            if (parts.length < 3) {
                throw new XiaoLongException("Invalid format! Use: event [task] /from [start] /to [end]");
            }
            tasks[taskCount] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
            taskCount++;
            System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1]);
        } catch (IndexOutOfBoundsException e) {
            throw new XiaoLongException("Invalid format! Use: event [task] /from [start] /to [end]");
        }
    }

    public static void main(String[] args) {
        XiaoLong xiaoLong = new XiaoLong();
        xiaoLong.start();
    }
}
