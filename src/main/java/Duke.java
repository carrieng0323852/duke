import java.util.*;

public class Duke {
    public static void main (String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String ab = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>(100);

        while (true) {
            if ("bye".equals(ab)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(ab)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println((i + 1) + ". [" + t.getStatusIcon() + "] " + t.description);
                }
            } else if (ab.contains("done")) {
                String taskNum = ab.substring(5, ab.length());
                int taskNumber = Integer.parseInt(taskNum);
                Task doneTask = list.get(taskNumber - 1);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[\u2713] " + doneTask.description);
            } else {
                Task newTask = new Task(ab);
                System.out.println("added: " + ab);
                list.add(newTask);
            }
            ab = sc.nextLine();
        }
    }
}