import java.util.Scanner;

public class XiaoLong {
    public static void main(String[] args) {
        // Print the design
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
        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
                break;
            } else if(input.equalsIgnoreCase("list")){
                if (taskCount == 0) {
                    System.out.println("List is empty. Please put any other input to add something, or say bye to exit!");
                }
                for (int i = 0; i < taskCount; i+=1) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }

            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = input;
                    taskCount += 1;
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
