import java.util.Scanner;

public class Mochizuki {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
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
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks[i]);
                    }
                }
                System.out.println(line);
                continue;
            }

            if (taskCount < tasks.length) {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(" added: " + input);
            } else {
                System.out.println(" The ledger is full. I can hold no more.");
            }
            System.out.println(line);
        }
    }
}
