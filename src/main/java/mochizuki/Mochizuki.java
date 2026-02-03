package mochizuki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mochizuki.exception.MochizukiException;
import mochizuki.task.Deadline;
import mochizuki.task.Event;
import mochizuki.task.Task;
import mochizuki.task.Todo;

public class Mochizuki {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println(" Konbanwa! I'm Mochizuki, keeper of the moonlit to-do list.");
        System.out.println(" Tell me your command.");
        System.out.println(line);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(line);
            try {
                if (handleInput(input, tasks, line)) {
                    break;
                }
            } catch (MochizukiException e) {
                System.out.println(" " + e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static boolean handleInput(String input, List<Task> tasks, String line)
            throws MochizukiException {
        if ("bye".equals(input)) {
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println(line);
            return true;
        }
        if ("list".equals(input)) {
            if (tasks.isEmpty()) {
                System.out.println(" The shrine shelves are empty.");
            } else {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i).formatForList());
                }
            }
            System.out.println(line);
            return false;
        }

        if (input.startsWith("mark ")) {
            int index = parseIndex(input.substring(5));
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks.get(index).formatForList());
            } else {
                throw new MochizukiException("That task number does not exist. Try `list` to see valid numbers.");
            }
            System.out.println(line);
            return false;
        }

        if (input.startsWith("unmark ")) {
            int index = parseIndex(input.substring(7));
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(index).formatForList());
            } else {
                throw new MochizukiException("That task number does not exist. Try `list` to see valid numbers.");
            }
            System.out.println(line);
            return false;
        }

        if ("delete".equals(input)) {
            throw new MochizukiException("Tell me which task to delete, e.g., `delete 2`.");
        }
        if (input.startsWith("delete ")) {
            int index = parseIndex(input.substring(7));
            if (index >= 0 && index < tasks.size()) {
                Task removed = tasks.remove(index);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removed.formatForList());
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new MochizukiException("That task number does not exist. Try `list` to see valid numbers.");
            }
            System.out.println(line);
            return false;
        }

        if ("todo".equals(input)) {
            throw new MochizukiException("A to-do needs a description after `todo`.");
        }
        if (input.startsWith("todo ")) {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new MochizukiException("A to-do needs a description after `todo`.");
            }
            tasks.add(new Todo(description));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.getLast().formatForList());
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
            return false;
        }

        if ("deadline".equals(input)) {
            throw new MochizukiException("A deadline needs a description and a /by time.");
        }
        if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf(" /by ");
            String description = byIndex >= 0 ? input.substring(9, byIndex).trim() : "";
            String by = byIndex >= 0 ? input.substring(byIndex + 5).trim() : "";
            if (description.isEmpty() || by.isEmpty()) {
                throw new MochizukiException("A deadline needs a description and a /by time.");
            }
            tasks.add(new Deadline(description, by));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.getLast().formatForList());
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
            return false;
        }

        if ("event".equals(input)) {
            throw new MochizukiException("An event needs a description, /from time, and /to time.");
        }
        if (input.startsWith("event ")) {
            int fromIndex = input.indexOf(" /from ");
            int toIndex = input.indexOf(" /to ");
            String description = fromIndex >= 0 ? input.substring(6, fromIndex).trim() : "";
            String from = (fromIndex >= 0 && toIndex > fromIndex)
                    ? input.substring(fromIndex + 7, toIndex).trim()
                    : "";
            String to = toIndex >= 0 ? input.substring(toIndex + 5).trim() : "";
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new MochizukiException("An event needs a description, /from time, and /to time.");
            }
            tasks.add(new Event(description, from, to));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.getLast().formatForList());
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
            return false;
        }

        throw new MochizukiException("I don't recognize that incantation. Try `list`, `todo`, `deadline`, or `event`.");
    }

    private static int parseIndex(String raw) {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
