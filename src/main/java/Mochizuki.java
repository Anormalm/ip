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

            if (taskCount < tasks.length) {
                tasks[taskCount] = new Task(input);
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

    private static class Task {
        private final String description;
        private boolean isDone;

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
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }
}
