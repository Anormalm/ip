import java.util.Scanner;

public class Mochizuki {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);

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
            System.out.println(" " + input);
            System.out.println(line);
        }
    }
}
