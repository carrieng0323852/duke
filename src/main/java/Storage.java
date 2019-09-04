import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.Pattern;

//0 is incomplete 1 is complete

public class Storage {

    private Scanner sc;
    private File dataFolder;
    private File data;

    public Storage(File dataFolder, File data) {
        this.dataFolder = dataFolder;
        this.data = data;
    }

    public void openFile() throws IOException {
        sc = new Scanner(data);
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<>(100);
        if (data.exists()) {
            openFile();
            readInput(list);
            closeFile();
        } else {
            dataFolder.mkdir();
            FileWriter wr = new FileWriter(data);
        }
        return list;
    }

    public void readInput(ArrayList<Task> list) throws IOException {
        while (sc.hasNextLine()) {
            String ab = sc.nextLine();
            String[] output = ab.split(Pattern.quote(" | "));
            if (output.length == 3) { //if todo
                ToDo newToDo = new ToDo(output[2]); //output2 is description
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
        fw.write("D | 0 | " + output1 + " | " + tokens[0] + Time.getsuffix(Integer.parseInt(tokens[0])) + " of " + Time.getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + Time.getHours(token[1]) + ":" + token[1].charAt(2) + token[1].charAt(3) + Time.convert12(token[1]));
        fw.newLine();
        fw.close();
    }

    public void addEvent(String output1, String output2) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter("C:\\Users\\65839\\duke\\data\\duke.txt",true));
        String[] token = output2.split(Pattern.quote(" "));
        String[] tokens = token[0].split(Pattern.quote("/"));
        fw.write("E | 0 | " + output1 + " | " + tokens[0] + Time.getsuffix(Integer.parseInt(tokens[0])) + " of " + Time.getMonth(Integer.parseInt(tokens[1])) + " " + tokens[2] + ", " + Time.getHours(token[1]) + ":" + token[1].charAt(2) + token[1].charAt(3) + Time.convert12(token[1]) );
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

    public void closeFile() {
        sc.close();
    }
}
