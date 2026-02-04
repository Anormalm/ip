package mochizuki.command;

import java.time.LocalDate;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Event;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Adds an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Creates an event command.
     *
     * @param description task description
     * @param from start date
     * @param to end date
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the task and persists the change.
     *
     * @param tasks task list
     * @param ui user interface
     * @param storage storage handler
     * @throws MochizukiException if input is invalid or save fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException {
        if (description.isEmpty() || from == null || to == null) {
            throw new MochizukiException("An event needs a description, /from time, and /to time.");
        }
        tasks.add(new Event(description, from, to));
        ui.showAdded(tasks.get(tasks.size() - 1), tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
