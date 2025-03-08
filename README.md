# XiaoLong Chatbot User Guide

XiaoLong is a simple task management chatbot that helps you keep track of your to-dos, deadlines, and events.

## Getting Started

1. Run XiaoLong using the command:
   ```
   java -jar XiaoLong.jar
   ```
2. Use commands to add, view, and manage your tasks
3. Tasks are automatically saved between sessions

## Available Commands

### Adding Tasks

| Command | Format | Example | Description |
|---------|--------|---------|-------------|
| `todo` | `todo TASK_DESCRIPTION` | `todo read book` | Adds a simple to-do task |
| `deadline` | `deadline TASK_DESCRIPTION /by DEADLINE` | `deadline return book /by June 6th` | Adds a task with a deadline |
| `event` | `event TASK_DESCRIPTION /from START_TIME /to END_TIME` | `event team meeting /from Monday 2pm /to Monday 4pm` | Adds an event with start and end times |

### Managing Tasks

| Command | Format | Example | Description |
|---------|--------|---------|-------------|
| `list` | `list` | `list` | Shows all tasks |
| `mark` | `mark TASK_NUMBER` | `mark 3` | Marks a task as done |
| `unmark` | `unmark TASK_NUMBER` | `unmark 2` | Marks a task as not done |
| `delete` | `delete TASK_NUMBER` | `delete 1` | Removes a task |
| `find` | `find KEYWORD` | `find book` | Finds tasks containing the keyword |
| `bye` | `bye` | `bye` | Exits the program |

## Task Formats

Tasks are displayed in the following format:

- **To-Do**: `[T][✓] read book` (✓ means done, ✗ means not done)
- **Deadline**: `[D][✓] return book (by: June 6th)`
- **Event**: `[E][✓] team meeting (from: Monday 2pm to: Monday 4pm)`

## Examples

### Adding Different Types of Tasks

```
todo finish homework
deadline complete project /by Friday
event doctor appointment /from Tuesday 10am /to Tuesday 11am
```

### Managing Your Tasks

```
list
mark 2
unmark 3
delete 1
```

### Finding Tasks

```
find project
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][✗] start new project
     2.[D][✓] complete project (by: Friday)
```

## Tips

- Task numbers start from 1 (not 0)
- All commands are case-insensitive (`TODO` works the same as `todo`)
- Your tasks are automatically saved to a file for your next session
- Use specific keywords when searching to find relevant tasks more easily

## Error Messages

If you see an error message, check that you're using the correct command format:

- For deadlines, use `/by` to specify the due date
- For events, use both `/from` and `/to` to specify start and end times
- Task numbers must be valid (can't mark or delete task #5 if you only have 3 tasks)
