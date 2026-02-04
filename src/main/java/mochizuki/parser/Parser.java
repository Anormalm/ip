package mochizuki.parser;

import mochizuki.command.Command;
import mochizuki.command.DeleteCommand;
import mochizuki.command.DeadlineCommand;
import mochizuki.command.EventCommand;
import mochizuki.command.ExitCommand;
import mochizuki.command.ListCommand;
import mochizuki.command.MarkCommand;
import mochizuki.command.TodoCommand;
import mochizuki.command.UnmarkCommand;
import mochizuki.exception.MochizukiException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws MochizukiException {
        if ("bye".equals(input)) {
            return new ExitCommand();
        }
        if ("list".equals(input)) {
            return new ListCommand();
        }
        if ("mark".equals(input)) {
            throw new MochizukiException("Tell me which task to mark, e.g., `mark 2`.");
        }
        if (input.startsWith("mark ")) {
            return new MarkCommand(parseIndex(input.substring(5)));
        }
        if ("unmark".equals(input)) {
            throw new MochizukiException("Tell me which task to unmark, e.g., `unmark 2`.");
        }
        if (input.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(input.substring(7)));
        }
        if ("delete".equals(input)) {
            throw new MochizukiException("Tell me which task to delete, e.g., `delete 2`.");
        }
        if (input.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(input.substring(7)));
        }
        if ("todo".equals(input)) {
            throw new MochizukiException("A to-do needs a description after `todo`.");
        }
        if (input.startsWith("todo ")) {
            return new TodoCommand(input.substring(5).trim());
        }
        if ("deadline".equals(input)) {
            throw new MochizukiException("A deadline needs a description and a /by time.");
        }
        if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf(" /by ");
            String description = byIndex >= 0 ? input.substring(9, byIndex).trim() : "";
            String by = byIndex >= 0 ? input.substring(byIndex + 5).trim() : "";
            return new DeadlineCommand(description, parseDate(by));
        }
        if ("event".equals(input)) {
            throw new MochizukiException("An event needs a description, /from time, and /to time.");
        }
        if (input.startsWith("event ")) {
            int fromIndex = input.indexOf(" /from ");
            int toIndex = input.indexOf(" /to ");
            String description = fromIndex >= 0 ? input.substring(6, fromIndex).trim() : "";
            String from = (fromIndex >= 0 && toIndex > fromIndex)
                    ? input.substring(fromIndex + 7, toIndex).trim()
                    : "";
            String to = toIndex >= 0 ? input.substring(toIndex + 5).trim() : "";
            return new EventCommand(description, from, to);
        }
        throw new MochizukiException("I don't recognize that incantation. Try `list`, `todo`, `deadline`, or `event`.");
    }

    private static int parseIndex(String raw) throws MochizukiException {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MochizukiException("That task number looks wrong. Try `list` for the right index.");
        }
    }

    private static LocalDate parseDate(String raw) throws MochizukiException {
        try {
            return LocalDate.parse(raw.trim());
        } catch (DateTimeParseException e) {
            throw new MochizukiException("Dates should look like yyyy-mm-dd, e.g., 2019-12-02.");
        }
    }
}
