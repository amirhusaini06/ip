import java.util.Scanner;

public class XiaoLong {
    public static void main(String[] args) {
        String design =
                " ___    ___ ___  ________  ________  ___       ________  ________   ________     \n" +
                        "|\\  \\  /  /|\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\|\\   ___  \\|\\   ____\\    \n" +
                        "\\ \\  \\/  / | \\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\    \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\___|    \n" +
                        " \\ \\    / / \\ \\  \\ \\   __  \\ \\  \\\\  \\ \\  \\    \\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\  ___  \n" +
                        "  /     \\/   \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\ \\  \\____\\ \\  \\\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \n" +
                        " /  /\\   \\    \\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\\n" +
                        "/__/ /\\ __\\    \\|__|\\|__|\\|__|\\|_______|\\|_______|\\|_______|\\|__| \\|__|\\|_______|\n" +
                        "|__|/ \\|__|                                                                       ";

        System.out.println("___________________________________________________________________________________________________");
        System.out.println(design);
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("What can I do for you today? I can make a list for you. Just input anything. Say bye to exit!");
        System.out.println("___________________________________________________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
                break;

            } else if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("List is empty. Please put any other input to add something, or say bye to exit!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                    System.out.println("Input mark/unmark N to mark/unmark the Nth item on your list");
                }

            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks[taskNumber - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1].toString());

            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskNumber - 1].toString());

            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("Added to list: " + input);
                    System.out.println("Say list to show list, or say bye to exit!");
                } else {
                    System.out.println("Task list is full. Say list to show list, or say bye to exit!");
                }
            }
        }

        scanner.close();
    }
}
