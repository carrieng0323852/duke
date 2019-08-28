import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Pattern;

public class readFile {
    private Scanner sc;

    public void openFile() throws FileNotFoundException {
        sc = new Scanner(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));
    }

    public ArrayList<Task> readInput() {
        ArrayList<Task> list = new ArrayList<>(100);
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(Pattern.quote(" | "));
            if (output.length == 3) { //if todo
                Todo newToDo = new Todo(output[2]); //output2 is description
                if ("1".equals(output[1])) { //means completed
                    newToDo.markAsDone();
                } else {
                    newToDo.isDone = false;
                }
                list.add(newToDo);
            } else { //deadline or event
                if (output[0].equals("D")) {
                    Deadline newDeadline = new Deadline(output[2], output[3]);
                    if (output[1].equals("1")) {
                        newDeadline.markAsDone();
                    } else {
                        newDeadline.isDone = false;
                    }
                    list.add(newDeadline);
                } else {
                    if (output[0].equals("E")) {
                        Event newEvent = new Event(output[2], output[3]);
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
