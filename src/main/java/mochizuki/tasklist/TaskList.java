package mochizuki.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mochizuki.task.Task;
import mochizuki.task.Deadline;
import mochizuki.task.Event;

/**
 * Manages the collection of tasks and related queries.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the provided tasks.
     *
     * @param tasks initial tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns whether the list is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index zero-based index
     * @return task at index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the given index.
     *
     * @param index zero-based index
     * @return removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns an unmodifiable snapshot of the tasks.
     *
     * @return immutable task list
     */
    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }

    /**
     * Finds tasks occurring on a specific date.
     *
     * @param date target date
     * @return matching tasks
     */
    public List<Task> findByDate(LocalDate date) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline deadline) {
                if (deadline.getBy().equals(date)) {
                    matches.add(task);
                }
            } else if (task instanceof Event event) {
                if (!date.isBefore(event.getFrom()) && !date.isAfter(event.getTo())) {
                    matches.add(task);
                }
            }
        }
        return matches;
    }

    /**
     * Finds tasks whose descriptions contain the keyword (case-insensitive).
     *
     * @param keyword search keyword
     * @return matching tasks
     */
    public List<Task> findByKeyword(String keyword) {
        List<Task> matches = new ArrayList<>();
        String needle = keyword.toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(needle)) {
                matches.add(task);
            }
        }
        return matches;
    }
}
