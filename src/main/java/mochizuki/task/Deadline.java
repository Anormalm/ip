package mochizuki.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param description task description
     * @param by due date
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date.
     *
     * @return due date
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDetails() {
        return " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
