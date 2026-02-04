package mochizuki.task;

/**
 * Represents a task item in the list.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the list display format for the task.
     *
     * @return formatted task string
     */
    public String formatForList() {
        return getTypeIcon() + "[" + (isDone ? "X" : " ") + "] " + description + getDetails();
    }

    /**
     * Returns the type icon prefix, e.g. [T], [D], [E].
     *
     * @return type icon string
     */
    protected abstract String getTypeIcon();

    /**
     * Returns any extra details for display (default empty).
     *
     * @return details string
     */
    protected String getDetails() {
        return "";
    }

    /**
     * Serializes the task into a single line for storage.
     *
     * @return storage format line
     */
    public abstract String toDataString();
}
