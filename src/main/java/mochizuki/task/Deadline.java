package mochizuki.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    @Override
    protected String getDetails() {
        return " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
