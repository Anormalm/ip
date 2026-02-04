package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Todo;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Adds a to-do task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a to-do command.
     *
     * @param description task description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the task and persists the change.
     *
     * @param tasks task list
     * @param ui user interface
     * @param storage storage handler
     * @throws MochizukiException if the description is empty or save fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException {
        if (description.isEmpty()) {
            throw new MochizukiException("A to-do needs a description after `todo`.");
        }
        tasks.add(new Todo(description));
        ui.showAdded(tasks.get(tasks.size() - 1), tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
