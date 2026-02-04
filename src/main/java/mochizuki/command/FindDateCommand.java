package mochizuki.command;

import java.time.LocalDate;

import mochizuki.storage.Storage;
import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Finds tasks that occur on a specific date.
 */
public class FindDateCommand extends Command {
    private final LocalDate date;

    /**
     * Creates a find-date command.
     *
     * @param date target date
     */
    public FindDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Displays tasks that match the date.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDate(tasks.findByDate(date), date);
    }
}
