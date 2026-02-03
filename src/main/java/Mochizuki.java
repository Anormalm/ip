import java.util.Scanner;

public class Mochizuki {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(line);
        System.out.println(" Konbanwa! I'm Mochizuki, keeper of the moonlit to-do list.");
        System.out.println(" Tell me your command.");
        System.out.println(line);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(line);
            if ("bye".equals(input)) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if ("list".equals(input)) {
                if (taskCount == 0) {
                    System.out.println(" The shrine shelves are empty.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i].formatForList());
                    }
                }
                System.out.println(line);
                continue;
            }

            if (input.startsWith("mark ")) {
                int index = parseIndex(input.substring(5));
                if (index >= 0 && index < taskCount) {
                    tasks[index].markDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[index].formatForList());
                } else {
                    System.out.println(" That task number does not exist.");
                }
                System.out.println(line);
                continue;
            }

            if (input.startsWith("unmark ")) {
                int index = parseIndex(input.substring(7));
                if (index >= 0 && index < taskCount) {
                    tasks[index].markNotDone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[index].formatForList());
                } else {
                    System.out.println(" That task number does not exist.");
                }
                System.out.println(line);
                continue;
            }

            if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println(" A to-do needs a description.");
                } else if (taskCount < tasks.length) {
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1].formatForList());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else {
                    System.out.println(" The ledger is full. I can hold no more.");
                }
                System.out.println(line);
                continue;
            }

            if (input.startsWith("deadline ")) {
                int byIndex = input.indexOf(" /by ");
                String description = byIndex >= 0 ? input.substring(9, byIndex).trim() : "";
                String by = byIndex >= 0 ? input.substring(byIndex + 5).trim() : "";
                if (description.isEmpty() || by.isEmpty()) {
                    System.out.println(" A deadline needs a description and a /by time.");
                } else if (taskCount < tasks.length) {
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1].formatForList());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else {
                    System.out.println(" The ledger is full. I can hold no more.");
                }
                System.out.println(line);
                continue;
            }

            if (input.startsWith("event ")) {
                int fromIndex = input.indexOf(" /from ");
                int toIndex = input.indexOf(" /to ");
                String description = fromIndex >= 0 ? input.substring(6, fromIndex).trim() : "";
                String from = (fromIndex >= 0 && toIndex > fromIndex) ? input.substring(fromIndex + 7, toIndex).trim() : "";
                String to = toIndex >= 0 ? input.substring(toIndex + 5).trim() : "";
                if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    System.out.println(" An event needs a description, /from time, and /to time.");
                } else if (taskCount < tasks.length) {
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount - 1].formatForList());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else {
                    System.out.println(" The ledger is full. I can hold no more.");
                }
                System.out.println(line);
                continue;
            }

            if (taskCount < tasks.length) {
                tasks[taskCount] = new Todo(input);
                taskCount++;
                System.out.println(" added: " + input);
            } else {
                System.out.println(" The ledger is full. I can hold no more.");
            }
            System.out.println(line);
        }
    }

    private static int parseIndex(String raw) {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static abstract class Task {
        protected final String description;
        protected boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        void markDone() {
            isDone = true;
        }

        void markNotDone() {
            isDone = false;
        }

        String formatForList() {
            return getTypeIcon() + "[" + (isDone ? "X" : " ") + "] " + description + getDetails();
        }

        protected abstract String getTypeIcon();

        protected String getDetails() {
            return "";
        }
    }

    private static class Todo extends Task {
        Todo(String description) {
            super(description);
        }

        @Override
        protected String getTypeIcon() {
            return "[T]";
        }
    }

    private static class Deadline extends Task {
        private final String by;

        Deadline(String description, String by) {
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
    }

    private static class Event extends Task {
        private final String from;
        private final String to;

        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        protected String getTypeIcon() {
            return "[E]";
        }

        @Override
        protected String getDetails() {
            return " (from: " + from + " to: " + to + ")";
        }
    }
}
