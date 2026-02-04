package mochizuki.command;

import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Lists all tasks.
 */
public class ListCommand extends Command {
    /**
     * Displays the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
