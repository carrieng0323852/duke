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
                dukeException.checkCommand(ab);
            } catch (dukeException e) {
                System.out.println("error\n" + e);
            }
            //if command is to list and whether the list is empty
            if (ab.equals("list")) {
                try {
                    dukeException.checkList(list);
                    Command.printList(list);
                } catch (dukeException e) {
                    System.out.println("error\n" + e);
                }
            } else if (ab.equals("done")) { //else check if the valid command is done todo event or deadline
                ab = sc.nextLine();
                Command.taskDone(list, ab);
            } else if (ab.equals("delete")) {
                ab = sc.nextLine();
                Command.taskDelete(list, ab);
            } else if (ab.equals("find")) {
                ab = sc.nextLine();
                Command.taskFind(list, ab);
            } else {
                inputFile newFile = new inputFile();
                if (ab.equals("todo")) {
                    ab = sc.nextLine();
                    ToDo.addToDo(list, ab, newFile);
                } else if (ab.equals("deadline")) {
                    ab = sc.nextLine();
                    Deadline.addDeadline(list, ab, newFile);
                } else if (ab.equals("event")) {
                    ab = sc.nextLine();
                    Event.addEvent(list, ab, newFile);
                }
            }
            ab = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}