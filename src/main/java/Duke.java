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

        ArrayList<String> list = new ArrayList<String>();
        int count = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);
            String ab = sc.nextLine();
            if ("list".equals(ab)) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
            } else if ("bye".equals(ab)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                list.add(ab);
                System.out.println("added: " + ab);
                count++;
            }
        }
    }
}
