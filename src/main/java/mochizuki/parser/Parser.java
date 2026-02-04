package mochizuki.parser;

import mochizuki.command.Command;
import mochizuki.command.DeleteCommand;
import mochizuki.command.DeadlineCommand;
import mochizuki.command.EventCommand;
import mochizuki.command.FindCommand;
import mochizuki.command.FindDateCommand;
import mochizuki.command.ExitCommand;
import mochizuki.command.ListCommand;
import mochizuki.command.MarkCommand;
import mochizuki.command.SecretCommand;
import mochizuki.command.TodoCommand;
import mochizuki.command.UnmarkCommand;
import mochizuki.exception.MochizukiException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into executable commands.
 */
public class Parser {
    /**
     * Parses a full command string into a Command object.
     *
     * @param input raw user input
     * @return parsed command
     * @throws MochizukiException if the command is invalid
     */
    public static Command parse(String input) throws MochizukiException {
        String trimmed = input.trim();
        if ("bye".equals(trimmed)) {
            return new ExitCommand();
        }
        if ("moon".equalsIgnoreCase(trimmed)) {
            return new SecretCommand("""
          _____
       .-'     '-.
     .'           '.
    /               \\
   |                 |
    \\               /
     '.           .'
       '-.___.-'
The moon remembers your steady steps.
""");
        }
        if ("eclipse".equalsIgnoreCase(trimmed)) {
            return new SecretCommand("""
       _____       _____
    .-'     '-._.-'     '-.
   /                       \\
  |                         |
  |      (        )         |
   \\                       /
    '-._______________,-'

A brief hush... then the world returns, brighter.
""");
        }
        if ("tide".equalsIgnoreCase(trimmed)) {
            return new SecretCommand("""
       ~    ~     ~   ~ ~
    ~     ~  ~      ~    ~
  ~   ~      ~   ~     ~   ~
      ~  ~    ~  ~    ~   ~
 ~   ~     ~    ~   ~      ~
Your tasks ebb and flow; you still command the shore.
""");
        }
        if ("list".equals(trimmed)) {
            return new ListCommand();
        }
        if ("find".equals(trimmed)) {
            throw new MochizukiException("Tell me what to find, e.g., `find book`.");
        }
        if (trimmed.startsWith("find ")) {
            return new FindCommand(trimmed.substring(5).trim());
        }
        if ("mark".equals(trimmed)) {
            throw new MochizukiException("Tell me which task to mark, e.g., `mark 2`.");
        }
        if (trimmed.startsWith("mark ")) {
            return new MarkCommand(parseIndex(trimmed.substring(5)));
        }
        if ("unmark".equals(trimmed)) {
            throw new MochizukiException("Tell me which task to unmark, e.g., `unmark 2`.");
        }
        if (trimmed.startsWith("unmark ")) {
            return new UnmarkCommand(parseIndex(trimmed.substring(7)));
        }
        if ("delete".equals(trimmed)) {
            throw new MochizukiException("Tell me which task to delete, e.g., `delete 2`.");
        }
        if (trimmed.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(trimmed.substring(7)));
        }
        if ("todo".equals(trimmed)) {
            throw new MochizukiException("A to-do needs a description after `todo`.");
        }
        if (trimmed.startsWith("todo ")) {
            return new TodoCommand(trimmed.substring(5).trim());
        }
        if ("deadline".equals(trimmed)) {
            throw new MochizukiException("A deadline needs a description and a /by time.");
        }
        if (trimmed.startsWith("deadline ")) {
            int byIndex = trimmed.indexOf(" /by ");
            String description = byIndex >= 0 ? trimmed.substring(9, byIndex).trim() : "";
            String by = byIndex >= 0 ? trimmed.substring(byIndex + 5).trim() : "";
            return new DeadlineCommand(description, parseDate(by));
        }
        if ("event".equals(trimmed)) {
            throw new MochizukiException("An event needs a description, /from time, and /to time.");
        }
        if (trimmed.startsWith("event ")) {
            int fromIndex = trimmed.indexOf(" /from ");
            int toIndex = trimmed.indexOf(" /to ");
            String description = fromIndex >= 0 ? trimmed.substring(6, fromIndex).trim() : "";
            String from = (fromIndex >= 0 && toIndex > fromIndex)
                    ? trimmed.substring(fromIndex + 7, toIndex).trim()
                    : "";
            String to = toIndex >= 0 ? trimmed.substring(toIndex + 5).trim() : "";
            return new EventCommand(description, parseDate(from), parseDate(to));
        }
        if ("find-date".equals(trimmed)) {
            throw new MochizukiException("Tell me the date, e.g., `find-date 2019-12-02`.");
        }
        if (trimmed.startsWith("find-date ")) {
            String dateRaw = trimmed.substring(10).trim();
            return new FindDateCommand(parseDate(dateRaw));
        }
        throw new MochizukiException("I don't recognize that incantation. Try `list`, `todo`, `deadline`, or `event`.");
    }

    /**
     * Parses a 1-based index token into a 0-based index.
     *
     * @param raw index token
     * @return zero-based index
     * @throws MochizukiException if the token is not a number
     */
    private static int parseIndex(String raw) throws MochizukiException {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new MochizukiException("That task number looks wrong. Try `list` for the right index.");
        }
    }

    /**
     * Parses a date in yyyy-mm-dd format.
     *
     * @param raw date token
     * @return parsed date
     * @throws MochizukiException if the date format is invalid
     */
    private static LocalDate parseDate(String raw) throws MochizukiException {
        try {
            return LocalDate.parse(raw.trim());
        } catch (DateTimeParseException e) {
            throw new MochizukiException("Dates should look like yyyy-mm-dd, e.g., 2026-02-02.");
        }
    }
}
