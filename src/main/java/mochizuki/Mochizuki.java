package mochizuki;

import java.nio.file.Path;
import java.nio.file.Paths;

import mochizuki.command.Command;
import mochizuki.exception.MochizukiException;
import mochizuki.parser.Parser;
import mochizuki.storage.Storage;
import mochizuki.tasklist.TaskList;
import mochizuki.ui.Ui;

/**
 * Entry point and main runtime for the Mochizuki chatbot.
 */
public class Mochizuki {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new chatbot instance wired to the given storage path.
     *
     * @param filePath path to the task storage file
     */
    public Mochizuki(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (MochizukiException e) {
            ui.showLoadingError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MochizukiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Launches the chatbot with the default storage location.
     *
     * @param args CLI arguments (unused)
     */
    public static void main(String[] args) {
        new Mochizuki(Paths.get("data", "mochizuki.txt")).run();
    }
}
