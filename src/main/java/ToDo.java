import java.io.IOException;
import java.util.*;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public String getType() {
        return ("[T]");
    }

    public static void addToDo (ArrayList<Task> list, String ab, Storage newFile){
        try {
            dukeException.checkDescription(ab);
            Task newToDo = new ToDo(ab);
            System.out.println("Got it. I've added this task:");
            System.out.println(newToDo.getType() + "[" + newToDo.getStatusIcon() + "] " + ab);
            list.add(newToDo);
            newFile.addTodo(ab.trim());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }
}