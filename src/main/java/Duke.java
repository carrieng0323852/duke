import com.sun.source.util.TaskListener;

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.DateFormatSymbols;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    File dataFolder = new File("C:\\Users\\65839\\duke\\data");
    File data = new File("C:\\Users\\65839\\duke\\data\\duke.txt");

    public Duke() throws IOException {
        storage = new Storage(dataFolder, data);
        tasks = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser(storage, ui);
    }

    public void run() throws IOException {
        ui.hello();
        String ab = ui.readCommand();
        while (!ab.equals("bye")) {
            try {
                dukeException.checkCommand(ab);
            } catch (dukeException e) {
                System.out.println("error\n" + e);
            }
            Command c = parser.parse(ab);
            c.toRun(tasks, ui, storage);
            ab = ui.readCommand();
        }
        ui.bye();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}