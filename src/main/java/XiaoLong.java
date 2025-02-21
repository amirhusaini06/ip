import java.io.*;
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

    private static final String FILE_PATH = "./data/xiaolong.txt";
    private ArrayList<Task> tasks;

    /**
     * Constructs a XiaoLong chatbot instance and loads tasks from storage.
     */
    public XiaoLong() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    /**
     * Starts the XiaoLong chatbot.
     */
    public void start() {
        System.out.println(DESIGN);
        System.out.println("Welcome to XiaoLong chatbot!");
        System.out.println("Type 'todo', 'deadline', 'event', 'delete', 'list', or 'bye'.");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! So long from XiaoLong.");
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

    private void handleMarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(taskNumber).markAsDone();
            saveTasksToFile();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskNumber));
        } catch (Exception e) {
            System.out.println("Please enter a valid task number to mark.");
        }
    }

    private void handleUnmarkCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(taskNumber).markAsNotDone();
            saveTasksToFile();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskNumber));
        } catch (Exception e) {
            System.out.println("Please enter a valid task number to unmark.");
        }
    }

    private void handleToDoCommand(String input) {
        try {
            String description = input.substring(5).trim();
            tasks.add(new ToDo(description));
            saveTasksToFile();
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } catch (Exception e) {
            System.out.println("Invalid ToDo format! Use: todo [task description]");
        }
    }

    private void handleDeadlineCommand(String input) {
        try {
            String[] parts = input.substring(9).split(" /by ");
            tasks.add(new Deadline(parts[0], parts[1]));
            saveTasksToFile();
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } catch (Exception e) {
            System.out.println("Invalid Deadline format! Use: deadline [task] /by [time]");
        }
    }

    private void handleEventCommand(String input) {
        try {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new IllegalArgumentException();
            }
            tasks.add(new Event(parts[0], parts[1], false));
            saveTasksToFile();
            System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
        } catch (Exception e) {
            System.out.println("Invalid Event format! Use: event [task] /from [start] /to [end]");
        }
    }

    private void handleDeleteCommand(String input) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = tasks.remove(taskNumber);
            saveTasksToFile();
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Invalid task number! Use: delete [task number]");
        }
    }

    private void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure the directory exists

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = Task.fromFileFormat(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        }
    }

    public static void main(String[] args) {
        XiaoLong xiaoLong = new XiaoLong();
        xiaoLong.start();
    }
}
