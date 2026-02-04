package mochizuki.command;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
