package mochizuki.command;

import java.time.LocalDate;

import mochizuki.storage.Storage;
import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

public class FindDateCommand extends Command {
    private final LocalDate date;

    public FindDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDate(tasks.findByDate(date), date);
    }
}
