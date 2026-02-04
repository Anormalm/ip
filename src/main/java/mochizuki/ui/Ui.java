package mochizuki.ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;

/**
 * Handles user interaction via standard input/output.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final Scanner scanner;

    /**
     * Creates a UI instance bound to standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the welcome banner.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Konbanwa! I'm Mochizuki, keeper of the moonlit to-do list.");
        System.out.println(" Tell me your command.");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a single-line message with a leading space.
     *
     * @param message message to display
     */
    public void showMessage(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays a storage loading error.
     *
     * @param message error message
     */
    public void showLoadingError(String message) {
        showLine();
        showMessage(message);
        showLine();
    }

    /**
     * Displays an error message.
     *
     * @param message error message
     */
    public void showError(String message) {
        showMessage(message);
    }

    /**
     * Displays the exit message.
     */
    public void showBye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the full task list.
     *
     * @param tasks task list
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showMessage("The shrine shelves are empty.");
            return;
        }
        showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).formatForList());
        }
    }

    /**
     * Displays a newly added task and current count.
     *
     * @param task added task
     * @param count updated count
     */
    public void showAdded(Task task, int count) {
        showMessage("Got it. I've added this task:");
        System.out.println("   " + task.formatForList());
        showMessage("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays a task marked as done.
     *
     * @param task updated task
     */
    public void showMarked(Task task) {
        showMessage("Nice! I've marked this task as done:");
        System.out.println("   " + task.formatForList());
    }

    /**
     * Displays a task marked as not done.
     *
     * @param task updated task
     */
    public void showUnmarked(Task task) {
        showMessage("OK, I've marked this task as not done yet:");
        System.out.println("   " + task.formatForList());
    }

    /**
     * Displays a deleted task and current count.
     *
     * @param task deleted task
     * @param count updated count
     */
    public void showDeleted(Task task, int count) {
        showMessage("Noted. I've removed this task:");
        System.out.println("   " + task.formatForList());
        showMessage("Now you have " + count + " tasks in the list.");
    }

    /**
     * Displays tasks occurring on a given date.
     *
     * @param tasks matching tasks
     * @param date date queried
     */
    public void showTasksOnDate(List<Task> tasks, LocalDate date) {
        if (tasks.isEmpty()) {
            showMessage("No tasks found on " + date.format(OUTPUT_FORMAT) + ".");
            return;
        }
        showMessage("Tasks on " + date.format(OUTPUT_FORMAT) + ":");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).formatForList());
        }
    }

    /**
     * Displays tasks that match a keyword search.
     *
     * @param tasks matching tasks
     */
    public void showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No matching tasks found.");
            return;
        }
        showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).formatForList());
        }
    }
}
