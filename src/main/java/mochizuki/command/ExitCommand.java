package mochizuki.command;

import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Exits the application.
 */
public class ExitCommand extends Command {
    /**
     * Prints the exit message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Indicates that this command ends the session.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
