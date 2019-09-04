import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Deadline extends Task {

    public Deadline(String description, String date, String suffix, String month, String year, int hour, char minute1, char minute2, String amorpm) {
        super(description);
        this.extra1 = date;
        this.extra2 = suffix;
        this.extra3 = month;
        this.extra4 = year;
        this.extra5 = hour;
        this.extra6 = minute1;
        this.extra7 = minute2;
        this.extra8 = amorpm;
    }

    public String getType() {
        return ("[D]");
    }

    public static void addDeadline(ArrayList<Task> list, String ab, inputFile newFile) {
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
}