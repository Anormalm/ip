package mochizuki.ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import mochizuki.task.Task;
import mochizuki.tasklist.TaskList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println(" Konbanwa! I'm Mochizuki, keeper of the moonlit to-do list.");
        System.out.println(" Tell me your command.");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(" " + message);
    }

    public void showLoadingError(String message) {
        showLine();
        showMessage(message);
        showLine();
    }

    public void showError(String message) {
        showMessage(message);
    }

    public void showBye() {
        showMessage("Bye. Hope to see you again soon!");
    }

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

    public void showAdded(Task task, int count) {
        showMessage("Got it. I've added this task:");
        System.out.println("   " + task.formatForList());
        showMessage("Now you have " + count + " tasks in the list.");
    }

    public void showMarked(Task task) {
        showMessage("Nice! I've marked this task as done:");
        System.out.println("   " + task.formatForList());
    }

    public void showUnmarked(Task task) {
        showMessage("OK, I've marked this task as not done yet:");
        System.out.println("   " + task.formatForList());
    }

    public void showDeleted(Task task, int count) {
        showMessage("Noted. I've removed this task:");
        System.out.println("   " + task.formatForList());
        showMessage("Now you have " + count + " tasks in the list.");
    }

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
