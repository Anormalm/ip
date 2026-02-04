package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an unmark command for the given index.
     *
     * @param index zero-based task index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task and persists the change.
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
        tasks.get(index).markNotDone();
        ui.showUnmarked(tasks.get(index));
        storage.save(tasks.asUnmodifiableList());
    }
}
