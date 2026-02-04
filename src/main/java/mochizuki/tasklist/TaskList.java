package mochizuki.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mochizuki.task.Task;
import mochizuki.task.Deadline;
import mochizuki.task.Event;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public List<Task> asUnmodifiableList() {
        return List.copyOf(tasks);
    }

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
