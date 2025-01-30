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
        System.out.println(design);  // Print the design
        System.out.println("What's up? I'm XiaoLong, your trusty chatbot");
        System.out.println("I can echo any input you make, give it a try!");
        System.out.println("___________________________________________________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Nice meeting you, so long from XiaoLong");
                break;
            } else {
                System.out.println(input);
            }

        }
        scanner.close();
    }
}
