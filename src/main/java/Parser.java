/**
 * Parses user input into commands.
 */
public class Parser {
    /**
     * Parses a user command string into a Command object.
     *
     * @param input The user input string.
     * @return A Command object.
     * @throws XiaoLongException If the command is invalid.
     */
    public Command parse(String input) throws XiaoLongException {
        String trimmedInput = input.trim();
        String[] parts = trimmedInput.split("\\s+", 2);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(parts);
            case "unmark":
                return parseUnmarkCommand(parts);
            case "todo":
                return parseToDoCommand(parts);
            case "deadline":
                return parseDeadlineCommand(parts);
            case "event":
                return parseEventCommand(parts);
            case "delete":
                return parseDeleteCommand(parts);
            case "find":
                return parseFindCommand(parts);
            default:
                throw new XiaoLongException("Invalid command! Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'delete', or 'find'.");
        }
    }

    // Existing methods remain the same...

    /**
     * Parses a find command.
     *
     * @param parts Command parts.
     * @return A FindCommand object.
     * @throws XiaoLongException If the command format is invalid.
     */
    private FindCommand parseFindCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new XiaoLongException("Please enter a keyword to search for tasks.");
        }
        return new FindCommand(parts[1].trim());
    }
}