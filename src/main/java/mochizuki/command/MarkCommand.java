package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a mark command for the given index.
     *
     * @param index zero-based task index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task and persists the change.
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
        tasks.get(index).markDone();
        ui.showMarked(tasks.get(index));
        storage.save(tasks.asUnmodifiableList());
    }
}
