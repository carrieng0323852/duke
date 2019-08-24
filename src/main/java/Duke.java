import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String ab = sc.nextLine();

            if ("bye".equals(ab)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(ab);
            }
        }
    }
}
