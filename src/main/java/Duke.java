import java.util.*;

public class Duke {
    public static void print(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getType().contains("D")) {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "]" + t.description + "(by:" + t.additionalInformation + ")");
            } else if (t.getType().contains("E")) {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "]" + t.description + "(at:" + t.additionalInformation + ")");
            } else {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + t.additionalInformation);
            }
        }
    }
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
                print(list);
            } else if (ab.contains("done")) {
                String taskNum = ab.substring(5, ab.length());
                int taskNumber = Integer.parseInt(taskNum);
                Task taskDone = list.get(taskNumber - 1);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskDone.getType() + "[" + taskDone.getStatusIcon() + "] " + taskDone.description);
            } else {
                if (ab.contains("todo")) {
                    String temp = ab.substring(5, ab.length());
                    Task newToDo = new Todo(temp);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newToDo.getType() + "[" + newToDo.getStatusIcon() +"]" + " " + temp);
                    list.add(newToDo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (ab.contains("deadline")) {
                    String temp = ab.substring(8, ab.length());
                    String[] output = temp.split("/by");
                    Task newDeadline = new Deadline(output[0], output[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.getType() + "[" + newDeadline.getStatusIcon() + "]" + " " + output[0] + " (by: " + output[1] + ")");
                    list.add(newDeadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else if (ab.contains("event")) {
                    String temp = ab.substring(5, ab.length());
                    String[] output = temp.split("/at");
                    Task newEvent = new Event(output[0], output[1]);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.getType() + "[" + newEvent.getStatusIcon() + "]" + " " + output[0] + " (at: " + output[1] + ")");
                    list.add(newEvent);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            }
            ab = sc.nextLine();
        }
    }
}