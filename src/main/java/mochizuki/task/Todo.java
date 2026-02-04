package mochizuki.task;

/**
 * Represents a simple to-do task without date information.
 */
public class Todo extends Task {
    /**
     * Creates a to-do task.
     *
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
