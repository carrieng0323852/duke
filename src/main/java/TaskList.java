import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> inputList) {
        list = inputList;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void printList() {
        try {
            dukeException.checkList(list);
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
        } catch (dukeException e) {
            System.out.println("error\n" + e);
        }
    }

    public void addDeadline(ArrayList<Task> list, String ab, Storage newFile) {
        String[] output = ab.split(Pattern.quote(" /by "));
        String[] token = output[1].split(Pattern.quote(" "));
        String[] tokens = token[0].split(Pattern.quote("/"));
        try {
            dukeException.checkDescription(output[0]);
            Task newDeadline = new Deadline(output[0], tokens[0], Time.getsuffix(Integer.parseInt(tokens[0])), Time.getMonth(Integer.parseInt(tokens[1])), tokens[2], Time.getHours(token[1]), token[1].charAt(2), token[1].charAt(3), Time.convert12(token[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println(newDeadline.getType() + "[" + newDeadline.getStatusIcon() + "] " + output[0] + " (by: " + tokens[0] + Time.getsuffix(Integer.parseInt(tokens[0])) + " of " + Time.getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + Time.getHours(token[1]) + ":" + (token[1].charAt(2)) + (token[1].charAt(3)) + Time.convert12(token[1]) + ")");
            list.add(newDeadline);
            newFile.addDeadline(output[0].trim(), output[1].trim());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }

    public void addEvent(ArrayList<Task> list, String ab, Storage newFile) {
        String[] output = ab.split(Pattern.quote(" /at "));
        String[] token = output[1].split(Pattern.quote(" "));
        String[] tokens = token[0].split(Pattern.quote("/"));
        try {
            dukeException.checkDescription(output[0]);
            Task newEvent = new Event(output[0], tokens[0], Time.getsuffix(Integer.parseInt(tokens[0])), Time.getMonth(Integer.parseInt(tokens[1])), tokens[2], Time.getHours(token[1]), token[1].charAt(2), token[1].charAt(3), Time.convert12(token[1]));
            System.out.println("Got it. I've added this task:");
            System.out.println(newEvent.getType() + "[" + newEvent.getStatusIcon() + "] " + output[0] + " (at: " + tokens[0] + Time.getsuffix(Integer.parseInt(tokens[0])) + " of " + Time.getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + Time.getHours(token[1]) + ":"  + (token[1].charAt(2)) + (token[1].charAt(3)) + Time.convert12(token[1])+ ")");
            list.add(newEvent);
            newFile.addEvent(output[0].trim(), output[1].trim());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }
}
