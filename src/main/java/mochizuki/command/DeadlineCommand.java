package mochizuki.command;

import java.time.LocalDate;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Deadline;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Creates a deadline command.
     *
     * @param description task description
     * @param by due date
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
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
        if (description.isEmpty() || by == null) {
            throw new MochizukiException("A deadline needs a description and a /by time.");
        }
        tasks.add(new Deadline(description, by));
        ui.showAdded(tasks.get(tasks.size() - 1), tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
