package mochizuki.command;

import java.time.LocalDate;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Deadline;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException {
        if (description.isEmpty() || by == null) {
            throw new MochizukiException("A deadline needs a description and a /by time.");
        }
        tasks.add(new Deadline(description, by));
        ui.showAdded(tasks.get(tasks.size() - 1), tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
