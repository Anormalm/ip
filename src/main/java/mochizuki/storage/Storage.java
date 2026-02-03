package mochizuki.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import mochizuki.exception.MochizukiException;
import mochizuki.task.Deadline;
import mochizuki.task.Event;
import mochizuki.task.Task;
import mochizuki.task.Todo;

public class Storage {
    private final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public List<Task> load() throws MochizukiException {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MochizukiException("I couldn't read the memory scrolls. Starting fresh.");
        }

        List<Task> tasks = new ArrayList<>();
        int corruptedCount = 0;

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            Task task = parseLine(line.trim());
            if (task == null) {
                corruptedCount++;
                continue;
            }
            tasks.add(task);
        }

        if (corruptedCount > 0) {
            System.out.println(" I brushed off " + corruptedCount + " smudged line(s) in the archive.");
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws MochizukiException {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(task.toDataString());
            }
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MochizukiException("I failed to ink your tasks to disk.");
        }
    }

    private Task parseLine(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        String doneFlag = parts[1];
        String description = parts[2];
        boolean isDone;

        if ("1".equals(doneFlag)) {
            isDone = true;
        } else if ("0".equals(doneFlag)) {
            isDone = false;
        } else {
            return null;
        }

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                return null;
            }
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            if (parts.length < 5) {
                return null;
            }
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            return null;
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }
}
