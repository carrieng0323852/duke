import java.io.*;
import java.text.DateFormatSymbols;
import java.util.*;
import java.lang.*;
import java.util.regex.Pattern;

//0 is incomplete 1 is complete

public class inputFile {

    private Scanner sc;
    private Formatter fm;

    public void openFile() throws FileNotFoundException {
        fm = new Formatter("C:\\Users\\65839\\duke\\data\\duke.txt"); //creates empty file
    }

    public void addTodo(String task) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt",true));
        fw.write("T | 0 | " + task);
        fw.newLine();
        fw.close();
    }

    public void addDeadline(String output1, String output2) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt", true));
        String[] token = output2.split(Pattern.quote(" "));
        String[] tokens = token[0].split(Pattern.quote("/"));
        fw.write("D | 0 | " + output1 + " | " + tokens[0] + getsuffix(Integer.parseInt(tokens[0])) + " of " + getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + getHours(token[1]) + ":" + token[1].charAt(2) + token[1].charAt(3) + convert12(token[1]));
        fw.newLine();
        fw.close();
    }

    public void addEvent(String output1, String output2) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt",true));
        String[] token = output2.split(Pattern.quote(" "));
        String[] tokens = token[0].split(Pattern.quote("/"));
        fw.write("E | 0 | " + output1 + " | " + tokens[0] + getsuffix(Integer.parseInt(tokens[0])) + " of " + getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + getHours(token[1]) + ":" + token[1].charAt(2) + token[1].charAt(3) + convert12(token[1]) );
        fw.newLine();
        fw.close();
    }

    public void doneTask(String description, String type) throws IOException {

        File currentFile = new File("C:\\Users\\65839\\duke\\data\\duke.txt");
        File temporaryFile = new File("C:\\Users\\65839\\duke\\data\\temporaryduke.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\temporaryduke.txt", true));
        PrintWriter print = new PrintWriter(out);
        sc = new Scanner(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(Pattern.quote(" | "));
            if (output[0].equals(type) && output[2].equals(description)) {
                if (output.length == 3) { //for todo
                    print.println(output[0] + " | 1 | " + output[2]);
                } else { //for deadline or event
                    print.println(output[0] + " | 1 | " + output[2] + " | " + output[3]);
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

    public void doneDelete(String description, String type) throws IOException {

        File currentFile = new File("C:\\Users\\65839\\duke\\data\\duke.txt");
        File temporaryFile = new File("C:\\Users\\65839\\duke\\data\\temporaryduke.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\temporaryduke.txt", true));
        PrintWriter print = new PrintWriter(out);
        sc = new Scanner(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(Pattern.quote(" | "));
            if (output[0].equals(type) && output[2].equals(description)) {
            } else {
                print.println(ab);
            }
        }
        sc.close();
        print.flush();
        print.close();
        currentFile.delete();
        temporaryFile.renameTo(new File("C:\\Users\\65839\\duke\\data\\duke.txt"));
    }

    static String getsuffix(int x) {
        if (x >= 11 || x <= 13) {
            return "th";
        } else {
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

    public void closeFile() {
        fm.close();
    }
}
