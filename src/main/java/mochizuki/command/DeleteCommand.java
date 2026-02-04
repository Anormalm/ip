package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a delete command for the given index.
     *
     * @param index zero-based task index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Removes the task and persists the change.
     *
     * @param tasks task list
     * @param ui user interface
     * @param storage storage handler
     * @throws MochizukiException if the index is invalid or save fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException {
        if (index < 0 || index >= tasks.size()) {
            throw new MochizukiException("That task number does not exist. Try `list` to see valid numbers.");
        }
        Task removed = tasks.remove(index);
        ui.showDeleted(removed, tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
