package mochizuki.command;

import java.time.LocalDate;

import mochizuki.exception.MochizukiException;
import mochizuki.storage.Storage;
import mochizuki.task.Event;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MochizukiException {
        if (description.isEmpty() || from == null || to == null) {
            throw new MochizukiException("An event needs a description, /from time, and /to time.");
        }
        tasks.add(new Event(description, from, to));
        ui.showAdded(tasks.get(tasks.size() - 1), tasks.size());
        storage.save(tasks.asUnmodifiableList());
    }
}
