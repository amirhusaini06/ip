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

    private MarkCommand parseMarkCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2) {
            throw new XiaoLongException("Please enter a task number to mark.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new XiaoLongException("Please enter a valid task number to mark.");
        }
    }

    private UnmarkCommand parseUnmarkCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2) {
            throw new XiaoLongException("Please enter a task number to unmark.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new XiaoLongException("Please enter a valid task number to unmark.");
        }
    }

    private ToDoCommand parseToDoCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new XiaoLongException("Invalid ToDo format! Use: todo [task description]");
        }
        return new ToDoCommand(parts[1].trim());
    }

    private DeadlineCommand parseDeadlineCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2 || !parts[1].contains("/by")) {
            throw new XiaoLongException("Invalid Deadline format! Use: deadline [task] /by [time]");
        }

        String[] components = parts[1].split(" /by ", 2);
        if (components.length < 2 || components[0].trim().isEmpty() || components[1].trim().isEmpty()) {
            throw new XiaoLongException("Invalid Deadline format! Use: deadline [task] /by [time]");
        }

        return new DeadlineCommand(components[0].trim(), components[1].trim());
    }

    private EventCommand parseEventCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
            throw new XiaoLongException("Invalid Event format! Use: event [task] /from [start] /to [end]");
        }

        try {
            // Extract description (everything before "/from")
            int fromIndex = parts[1].indexOf("/from");
            String description = parts[1].substring(0, fromIndex).trim();

            // Extract the part after "/from"
            String afterFrom = parts[1].substring(fromIndex + 5).trim();

            // Extract start time (everything before "/to")
            int toIndex = afterFrom.indexOf("/to");
            if (toIndex == -1) {
                throw new XiaoLongException("Invalid Event format! Use: event [task] /from [start] /to [end]");
            }

            String startTime = afterFrom.substring(0, toIndex).trim();
            String endTime = afterFrom.substring(toIndex + 3).trim();

            if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                throw new XiaoLongException("Invalid Event format! Use: event [task] /from [start] /to [end]");
            }

            return new EventCommand(description, startTime, endTime);
        } catch (Exception e) {
            throw new XiaoLongException("Invalid Event format! Use: event [task] /from [start] /to [end]");
        }
    }

    private DeleteCommand parseDeleteCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2) {
            throw new XiaoLongException("Please enter a task number to delete.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new XiaoLongException("Please enter a valid task number to delete.");
        }
    }
    
    private FindCommand parseFindCommand(String[] parts) throws XiaoLongException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new XiaoLongException("Please enter a keyword to search for tasks.");
        }
        return new FindCommand(parts[1].trim());
    }
}