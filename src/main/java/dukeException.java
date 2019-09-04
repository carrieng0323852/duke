import java.util.*;

public class dukeException extends Exception {

    public dukeException (String message) {
        super(message);
    }

    public static void checkCommand(String input) throws dukeException {
        String[] commands = {"list", "done", "todo", "deadline", "event", "delete", "find"};
        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (commands[i].equals(input)) {
                count++;
            }
        }
        if (count == 0) {
            throw new dukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void checkList(ArrayList<Task> list) throws dukeException {
        if (list.isEmpty()) {
            throw new dukeException("☹ OOPS!!! There are no tasks at the moment");
        }
    }

    //check if the task to be ticked is a valid one or whether it had been completed before already
    public static void checkTask(int x, ArrayList<Task> list) throws dukeException {
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

    public static void checkDelete(int x, ArrayList<Task> list) throws dukeException {
        if (x < 0 || x >= list.size()) {
            throw new dukeException("☹ OOPS!!! I'm sorry, but there is no such task number.");
        }
    }

    //check if todo deadline and event has a description
    public static void checkDescription(String input) throws dukeException {
        if (input.isEmpty()) {
            throw new dukeException("☹ OOPS!!! The description cannot be empty.");
        }
    }

    public static void checkDue(String input) throws dukeException {
        if (!input.contains("/by") || !input.contains("/at")) {
            throw new dukeException("☹ OOPS!!! There should be a date.");
        }
    }
}