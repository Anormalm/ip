package mochizuki.command;

import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Finds tasks by keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a find command for the given keyword.
     *
     * @param keyword search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays matching tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks.findByKeyword(keyword));
    }
}
