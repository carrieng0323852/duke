import java.util.*;

public class Duke {
    public static void main (String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String ab = sc.next();
        ArrayList<Task> list = new ArrayList<>(100);

        while (!ab.equalsIgnoreCase("bye")) {
            //firstly checking if the command exists
            try {
             checkCommand(ab);
            } catch (dukeException e) {
                System.out.println("error\n" + e);
            }
            //if command is to list and whether the list is empty
            if ("list".equals(ab)) {
                try {
                    checkList(list);
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
                } catch (dukeException e) {
                    System.out.println("error\n" + e);
                }
            } else if (ab.equals("done")) { //else check if the valid command is done todo event or deadline
                ab = sc.nextLine();
                String cd = ab.substring(1, ab.length());
                int taskNumber = Integer.parseInt(cd);
                try {
                    checkTask((taskNumber - 1), list);
                    Task taskDone = list.get(taskNumber - 1);
                    taskDone.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskDone.getType() + "[" + taskDone.getStatusIcon() + "] " + taskDone.description);
                } catch (dukeException e) {
                    System.out.println("error\n" + e);
                }
            } else {
                if (ab.equals("todo")) {
                    ab = sc.nextLine();
                    try {
                        checkDescription(ab);
                        Task newToDo = new Todo(ab);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newToDo.getType() + "[" + newToDo.getStatusIcon() + "]" + " " + ab);
                        list.add(newToDo);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (dukeException e) {
                        System.out.println("error\n" + e);
                    }
                } else if (ab.equals("deadline")) {
                    ab = sc.nextLine();
                    String[] output = ab.split(" /by ");
                    try {
                        checkDescription(output[0]);
                        Task newDeadline = new Deadline(output[0], output[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline.getType() + "[" + newDeadline.getStatusIcon() + "]" + " " + output[0] + " (by: " + output[1] + ")");
                        list.add(newDeadline);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (dukeException e) {
                        System.out.println("error\n" + e);
                    }
                } else if (ab.equals("event")) {
                    ab = sc.nextLine();
                    String[] output = ab.split(" /at ");
                    try {
                        checkDescription(output[0]);
                        Task newEvent = new Event(output[0], output[1]);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent.getType() + "[" + newEvent.getStatusIcon() + "]" + " " + output[0] + " (at: " + output[1] + ")");
                        list.add(newEvent);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (dukeException e) {
                        System.out.println("error\n" + e);
                    }
                }
            }
            ab = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //check if the command word is one of the 5
    static void checkCommand(String input) throws dukeException {
        String[] commands = {"list", "done", "todo", "deadline", "event"};
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (commands[i].equals(input)) {
                count++;
            }
        }
        if (count == 0) {
            throw new dukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    //check whether list is empty
    static void checkList(ArrayList<Task> list) throws dukeException {
        if (list.isEmpty()) {
            throw new dukeException("☹ OOPS!!! There are no tasks at the moment");
        }
    }
    //check if the task to be ticked is a valid one or whether it had been completed before already
    static void checkTask(int x, ArrayList<Task> list) throws dukeException {
        if (x < 0 || x >= list.size()) {
            throw new dukeException("☹ OOPS!!! I'm sorry, but there is no such task number.");
        } else {
            Task checkDone = list.get(x);
            String temp = checkDone.getStatusIcon();
            if ("\u2713".equals(temp)) {
                throw new dukeException("☹ OOPS!!! I'm sorry, but the task has already been completed!");
            }
        }
    }
    //check if todo deadline and event has a description
    static void checkDescription(String input) throws dukeException {
        if (input.isEmpty()) {
            throw new dukeException("☹ OOPS!!! The description cannot be empty.");
        }
    }
}