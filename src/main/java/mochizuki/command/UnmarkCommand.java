package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
