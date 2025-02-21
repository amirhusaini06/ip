import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot program that tracks different types of tasks.
 */
public class XiaoLong {
    private static final String DESIGN =
            " ___    ___ ___  ________  ________  ___       ________  ________   ________     \n" +
                    "|\\  \\  /  /|\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ___  \\|\\   ____\\    \n" +
                    "\\ \\  \\/  / | \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\___|    \n" +
                    " \\ \\    / / \\ \\  \\ \\   __  \\ \\  \\\\  \\ \\  \\    \\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\  ___  \n" +
                    "  /     \\/   \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\ \\  \\____\\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \n" +
                    " /  /\\   \\    \\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\\n" +
                    "/__/ /\\ __\\    \\|__|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|__| \\|__|\\|_______|\n" +
                    "|__|/ \\|__|                                                                       ";

    private ArrayList<Task> tasks;

    /**
     * Constructs a XiaoLong chatbot instance.
     */
    public XiaoLong() {
        tasks = new ArrayList<>();
    }

    /**
     * Starts the XiaoLong chatbot.
     */
    public void start() {
        System.out.println(DESIGN);
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("I can manage tasks for you! Say 'todo', 'deadline', 'event', or 'delete'. Say 'bye' to exit.");
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
            } else if (input.startsWith("todo")) {
                handleToDoCommand(input);
            } else if (input.startsWith("deadline")) {
                handleDeadlineCommand(input);
            } else if (input.startsWith("event")) {
                handleEventCommand(input);
            } else if (input.startsWith("delete")) {
                handleDeleteCommand(input);
            } else {
                System.out.println("Invalid command! Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', or 'delete'.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the list of tasks.
     */
    private void displayTasks() {
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
     * Handles marking a task as done.
     */
    private void handleMarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNumber));
        } catch (Exception e) {
            System.out.println("Please enter a valid task number to mark.");
        }
    }

    /**
     * Handles unmarking a task as not done.
     */
    private void handleUnmarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(taskNumber).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskNumber));
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
            tasks.add(new ToDo(description));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
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
            tasks.add(new Deadline(parts[0], parts[1]));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
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
            tasks.add(new Event(parts[0], parts[1], parts[2]));
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } catch (Exception e) {
            System.out.println("Invalid Event format! Use: event [task] /from [start] /to [end]");
        }
    }

    /**
     * Handles deleting a task.
     */
    private void handleDeleteCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Invalid task number! Use: delete [task number]");
        }
    }

    public static void main(String[] args) {
        XiaoLong xiaoLong = new XiaoLong();
        xiaoLong.start();
    }
}
