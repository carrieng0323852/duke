import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;

public class readFile {
    private Scanner sc;

    public void openFile() throws FileNotFoundException {
        sc = new Scanner(new File("C:\\Users\\65839\\duke\\data\\duke.txt")); //scans original file if it exists
    }

    public ArrayList<Task> readInput() {
        ArrayList<Task> list = new ArrayList<>(100);
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(Pattern.quote(" | "));
            if (output.length == 3) { //if todo
                Todo newToDo = new Todo(output[2]); //output2 is description
                if (output[1].equals("1")) { //means completed
                    newToDo.markAsDone();
                } else {
                    newToDo.isDone = false;
                }
                list.add(newToDo);
            } else { //deadline or event
                if (output[0].equals("D")) {
                    String[] token = output[3].split(Pattern.quote(" "));
                    char d;
                    String date, suffix;
                    if (token[0].length() == 3) {
                        d = token[0].charAt(0);
                        date = Character.toString(d);
                        suffix = token[0].substring(1, 3);
                    } else {
                        date = token[0].substring(0, 2);
                        suffix = token[0].substring(2, 4);
                    }
                    String[] tokens = token[4].split(Pattern.quote(":"));
                    Deadline newDeadline = new Deadline(output[2], date, suffix, token[2], token[3].substring(0, (token[3].length() - 1)), Integer.parseInt(tokens[0]), tokens[1].charAt(0), tokens[1].charAt(1), tokens[1].substring(2, 4));
                    if (output[1].equals("1")) {
                        newDeadline.markAsDone();
                    } else {
                        newDeadline.isDone = false;
                    }
                    list.add(newDeadline);
                } else {
                    if (output[0].equals("E")) {
                        String[] token = output[3].split(Pattern.quote(" "));
                        char d;
                        String date, suffix;
                        if (token[0].length() == 3) {
                            d = token[0].charAt(0);
                            date = Character.toString(d);
                            suffix = token[0].substring(1, 3);
                        } else {
                            date = token[0].substring(0, 2);
                            suffix = token[0].substring(2, 4);
                        }
                        String[] tokens = token[4].split(Pattern.quote(":"));
                        Event newEvent = new Event(output[2], date, suffix, token[2], token[3].substring(0, (token[3].length() - 1)), Integer.parseInt(tokens[0]), tokens[1].charAt(0), tokens[1].charAt(1), tokens[1].substring(2, 4));
                        if (output[1].equals("1")) {
                            newEvent.markAsDone();
                        } else {
                            newEvent.isDone = false;
                        }
                        list.add(newEvent);
                    }
                }
            }
        }
        return list;
    }

    public void closeFile() {
        sc.close();
    }
}
