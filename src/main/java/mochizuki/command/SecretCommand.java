package mochizuki.command;

import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Displays a hidden response for easter egg commands.
 */
public class SecretCommand extends Command {
    private final String message;

    /**
     * Creates a secret command with a custom response.
     *
     * @param message response message
     */
    public SecretCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(message);
    }
}
