import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.DateFormatSymbols;

public class Duke {
    public static void main (String[] args) throws IOException {
        ArrayList<Task> list = new ArrayList<>(100);
        File dataFolder = new File ("C:\\Users\\65839\\duke\\data");
        File data = new File("C:\\Users\\65839\\duke\\data\\duke.txt");
        if (data.exists()) {
            readFile readIn = new readFile(); //if file exists run readfile
            readIn.openFile();
            list = readIn.readInput();
            readIn.closeFile();
        } else {
            System.out.println("File does not exist");
            dataFolder.mkdir();
            FileWriter wr = new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt");
        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String ab = sc.next();

        while (!ab.equals("bye")) {
            //firstly checking if the command exists
            try {
             checkCommand(ab);
            } catch (dukeException e) {
                System.out.println("error\n" + e);
            }
            //if command is to list and whether the list is empty
            if (ab.equals("list")) {
                try {
                    checkList(list);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        if (t.getType().contains("D")) {
                            System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + " (by: " + t.extra1 + t.extra2 + " of " + t.extra3 + " " + t.extra4 + ", " + t.extra5 + ":" + t.extra6 + t.extra7 + t.extra8 + ")");
                        } else if (t.getType().contains("E")) {
                            System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + " (at: " + t.extra1 + t.extra2 + " of " + t.extra3 + " " + t.extra4 + ", " + t.extra5 + ":" + t.extra6 + t.extra7 + t.extra8 + ")");
                        } else {
                            System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + t.additionalInformation);
                        }
                    }
                } catch (dukeException e) {
                    System.out.println("error\n" + e);
                }
            } else if (ab.equals("done")) { //else check if the valid command is done todo event or deadline
                ab = sc.nextLine();
                //String[] output = ab.split(Pattern.quote(" "));
                String taskNum = ab.substring(1, ab.length());
                int taskNumber = Integer.parseInt(taskNum) - 1;
                //System.out.println(taskNumber);
                try {
                    checkTask(taskNumber, list);
                    Task taskDone = list.get(taskNumber);
                    taskDone.markAsDone();
                    inputFile newDone = new inputFile();
                    String type;
                    if (taskDone.getType().equals("[T]")) {
                        type = "T";
                    } else if (taskDone.getType().equals("[D]")) {
                        type = "D";
                    } else {
                        type = "E";
                    }
                    newDone.doneTask(taskDone.description, type);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskDone.getType() + "[" + taskDone.getStatusIcon() + "] " + taskDone.description);
                } catch (dukeException e) {
                    System.out.println("error\n" + e);
                }
            } else {
                inputFile newFile = new inputFile();
                if (ab.equals("todo")) {
                    ab = sc.nextLine();
                    try {
                        checkDescription(ab);
                        Task newToDo = new Todo(ab);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newToDo.getType() + "[" + newToDo.getStatusIcon() + "] " + ab);
                        list.add(newToDo);
                        newFile.addTodo(ab.trim());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (dukeException e) {
                        System.out.println("error\n" + e);
                    }
                } else if (ab.equals("deadline")) {
                    ab = sc.nextLine();
                    String[] output = ab.split(Pattern.quote(" /by "));
                    String[] token = output[1].split(Pattern.quote(" "));
                    String[] tokens = token[0].split(Pattern.quote("/"));
                    try {
                        checkDescription(output[0]);
                        Task newDeadline = new Deadline(output[0], tokens[0], getsuffix(Integer.parseInt(tokens[0])), getMonth(Integer.parseInt(tokens[1])), tokens[2], getHours(token[1]), token[1].charAt(2), token[1].charAt(3), convert12(token[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline.getType() + "[" + newDeadline.getStatusIcon() + "] " + output[0] + " (by: " + tokens[0] + getsuffix(Integer.parseInt(tokens[0])) + " of " + getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + getHours(token[1]) + ":" + (token[1].charAt(2)) + (token[1].charAt(3)) + convert12(token[1]) + ")");
                        list.add(newDeadline);
                        newFile.addDeadline(output[0].trim(), output[1].trim());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } catch (dukeException e) {
                        System.out.println("error\n" + e);
                    }
                } else if (ab.equals("event")) {
                    ab = sc.nextLine();
                    String[] output = ab.split(Pattern.quote(" /at "));
                    String[] token = output[1].split(Pattern.quote(" "));
                    String[] tokens = token[0].split(Pattern.quote("/"));
                    try {
                        checkDescription(output[0]);
                        Task newEvent = new Event(output[0], tokens[0], getsuffix(Integer.parseInt(tokens[0])), getMonth(Integer.parseInt(tokens[1])), tokens[2], getHours(token[1]), token[1].charAt(2), token[1].charAt(3), convert12(token[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent.getType() + "[" + newEvent.getStatusIcon() + "] " + output[0] + " (at: " + tokens[0] + getsuffix(Integer.parseInt(tokens[0])) + " of " + getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + getHours(token[1]) + ":"  + (token[1].charAt(2)) + (token[1].charAt(3)) + convert12(token[1])+ ")");
                        list.add(newEvent);
                        newFile.addEvent(output[0].trim(), output[1].trim());
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

    static String getsuffix(int x) {
        x %= 10;
        if (x == 1) {
            return "st";
        } else if (x == 2) {
            return "nd";
        } else if (x == 3) {
            return "rd";
        } else {
            return "th";
        }
    }

    static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    static int getHours(String str) {
        int h1 = (int) str.charAt(0) - '0';
        int h2 = (int) str.charAt(1) - '0';
        int hh = (h1 * 10) + h2;

        hh %= 12;

        if (hh == 0) {
            return 12;
        } else {
            return hh;
        }
    }

    static String convert12(String str) {
        int h1 = (int) str.charAt(0) - '0';
        int h2 = (int) str.charAt(1) - '0';
        int hh = (h1 * 10) + h2;

        if (hh < 12) {
            return "am";
        } else {
            return "pm";
        }
    }
}