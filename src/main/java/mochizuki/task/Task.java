package mochizuki.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String formatForList() {
        return getTypeIcon() + "[" + (isDone ? "X" : " ") + "] " + description + getDetails();
    }

    protected abstract String getTypeIcon();

    protected String getDetails() {
        return "";
    }
}
