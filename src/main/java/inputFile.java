import java.io.*;
import java.util.*;
import java.lang.*;

//0 is incomplete 1 is complete

public class inputFile {

    private Scanner sc;
    private Formatter fm;
    //private BufferedWriter bw;
    //private PrintWriter pw;

    public void openFile() throws FileNotFoundException {
        fm = new Formatter("C:\\Users\\65839\\duke\\data\\duke.txt"); //creates empty file
    }

    public void addTodo(String task) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt"));
        out.write("T | 0 | " + task);
        out.close();
    }

    public void addDeadline(String output1, String output2) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt"));
        out.write("D | 0 | " + output1 + " | " + output2);
        out.close();
    }

    public void addEvent(String output1, String output2) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt"));
        out.write("E | 0 | " + output1 + " | " + output2);
        out.close();
    }

    public void doneTask(String description, String type) throws IOException {

        File currentFile = new File("C:\\Users\\65839\\duke\\data\\duke.txt");
        File temporaryFile = new File("C:\\Users\\65839\\duke\\data\\temporaryduke.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt"));
        PrintWriter print = new PrintWriter(out);
        sc = new Scanner(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(" | ");
            if (output[0].equals(type)) {
                if (output[2].equals(description)) {
                    if (output.length == 3) { //for todo
                        print.println(output[0] + " | 1 | " + output[2]);
                    } else { //for deadline or event
                        print.println(output[0] + " | 1 | " + output[2] + " | " + output[3]);
                    }
                }
            } else {
                print.println(ab);
            }
        }
        sc.close();
        print.flush(); //to clear
        print.close();
        currentFile.delete();
        temporaryFile.renameTo(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));

    }

    public void closeFile() {
        fm.close();
    }
}

