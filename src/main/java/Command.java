import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.DateFormatSymbols;

public class Command {

    public static void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getType().contains("D")) {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + " (by: " + t.extra1 + t.extra2 + " of " + t.extra3 + " " + t.extra4 + ", " + t.extra5 + ":" + t.extra6 + t.extra7 + t.extra8 + ")");
            } else if (t.getType().contains("E")) {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description + " (at: " + t.extra1 + t.extra2 + " of " + t.extra3 + " " + t.extra4 + ", " + t.extra5 + ":" + t.extra6 + t.extra7 + t.extra8 + ")");
            } else {
                System.out.println((i + 1) + "." + t.getType() + "[" + t.getStatusIcon() + "] " + t.description);
            }
        }
    }

    public static void taskDone(ArrayList<Task> list, String ab) {
        String taskNum = ab.substring(1, ab.length());
        int taskNumber = Integer.parseInt(taskNum) - 1;
        try {
            dukeException.checkTask(taskNumber, list);
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
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }

    public static void taskDelete(ArrayList<Task> list, String ab) {
        String taskDel = ab.substring(1, ab.length());
        int index = Integer.parseInt(taskDel) - 1;
        try {
            dukeException.checkDelete(index, list);
            inputFile newDelete = new inputFile();
            Task taskDelete = list.get(index);
            System.out.println("Noted. I've removed this task:");
            String t = taskDelete.getType();
            String type = t.substring(1, (t.length() - 1));
            if (t.equals("[T]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description);
            } else if (t.equals("[D]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description + " (by: " + taskDelete.extra1 + taskDelete.extra2 + " of " + taskDelete.extra3 + " " + taskDelete.extra4 + ", " + taskDelete.extra5 + ":" + taskDelete.extra6 + taskDelete.extra7 + taskDelete.extra8 + ")");
            } else if (t.equals("[E]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description + " (at: " + taskDelete.extra1 + taskDelete.extra2 + " of " + taskDelete.extra3 + " " + taskDelete.extra4 + ", " + taskDelete.extra5 + ":" + taskDelete.extra6 + taskDelete.extra7 + taskDelete.extra8 + ")");
            }
            newDelete.doneDelete(taskDelete.description, type);
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }

    public static void taskFind(ArrayList<Task> list, String ab) {
        String taskDes= ab.substring(1, ab.length());
        boolean flag = true;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.contains(taskDes)) {
                count++;
                if (flag) {
                    System.out.println("Here are the matching tasks in your list:");
                    flag = false;
                }
                if (list.get(i).getType().equals("[T]")) {
                    System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                } else if (list.get(i).getType().equals("[D]")) {
                    System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description + " (by: " + list.get(i).extra1 + list.get(i).extra2 + " of " + list.get(i).extra3 + " " + list.get(i).extra4 + ", " + list.get(i).extra5 + ":" + list.get(i).extra6 + list.get(i).extra7 + list.get(i).extra8 + ")");
                } else if (list.get(i).getType().equals("[E]")) {
                    System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description + " (at: " + list.get(i).extra1 + list.get(i).extra2 + " of " + list.get(i).extra3 + " " + list.get(i).extra4 + ", " + list.get(i).extra5 + ":" + list.get(i).extra6 + list.get(i).extra7 + list.get(i).extra8 + ")");
                }
            }
        }
        if (count == 0) {
            System.out.println("☹ OOPS!!! I'm sorry, but there are no matching tasks in your list.");
        }
    }
}
