import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Event extends Task {

    public Event(String description, String date, String suffix, String month, String year, int hour, char minute1, char minute2, String amorpm) {
        super(description);
        //this.additionalInformation = at;
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
        return ("[E]");
    }

    public static void addEvent(ArrayList<Task> list, String ab, inputFile newFile) {
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