import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void hello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        return sc.next();
    }

    public String readDescription() {
        return sc.nextLine();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
