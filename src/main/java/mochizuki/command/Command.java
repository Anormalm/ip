package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Base type for executable user commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks task list
     * @param ui user interface
     * @param storage storage handler
     * @throws MochizukiException if execution fails
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException;

    /**
     * Returns whether this command should terminate the application.
     *
     * @return true if this is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
