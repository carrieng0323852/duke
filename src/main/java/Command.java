import java.util.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class Command {

    private Storage storage;
    private Ui ui;
    private String type;

    public Command (Storage storage, Ui ui, String type) {
        this.storage = storage;
        this.ui = ui;
        this.type = type;
    }

    public void taskDone(ArrayList<Task> list, String ab) {
        String taskNum = ab.substring(1, ab.length());
        int taskNumber = Integer.parseInt(taskNum) - 1;
        try {
            dukeException.checkDescription(taskNum);
            dukeException.checkTask(taskNumber, list);
            Task taskDone = list.get(taskNumber);
            taskDone.markAsDone();
            String type;
            if (taskDone.getType().equals("[T]")) {
                type = "T";
            } else if (taskDone.getType().equals("[D]")) {
                type = "D";
            } else {
                type = "E";
            }
            storage.doneTask(taskDone.description, type);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskDone.getType() + "[" + taskDone.getStatusIcon() + "] " + taskDone.description);
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }

    public void taskDelete(ArrayList<Task> list, String ab) {
        String taskDel = ab.substring(1, ab.length());
        int index = Integer.parseInt(taskDel) - 1;
        try {
            dukeException.checkDescription(taskDel);
            dukeException.checkDelete(index, list);
            Task taskDelete = list.get(index);
            System.out.println("Noted. I've removed this task:");
            String t = taskDelete.getType();
            String type = t.substring(1, (t.length() - 1));
            if (t.equals("[T]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description);
            } else if (t.equals("[D]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description + " (by: " + taskDelete.extra1 + taskDelete.extra2 + " of " + taskDelete.extra3 + " " + taskDelete.extra4 + ", " + taskDelete.extra5 + ":" + taskDelete.extra6 + taskDelete.extra7 + taskDelete.extra8 + ")");
            } else if (t.equals("[E]")) {
                System.out.println(t + "[" + taskDelete.getStatusIcon() + "] " + taskDelete.description + " (at: " + taskDelete.extra1 + taskDelete.extra2 + " of " + taskDelete.extra3 + " " + taskDelete.extra4 + ", " + taskDelete.extra5 + ":" + taskDelete.extra6 + taskDelete.extra7 + taskDelete.extra8 + ")");
            }
            storage.doneDelete(taskDelete.description, type);
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (dukeException | IOException e) {
            System.out.println("error\n" + e);
        }
    }

    public static void taskFind(ArrayList<Task> list, String ab) {
        String taskDes = ab.substring(1, ab.length());
        try {
            dukeException.checkDescription(taskDes);
            boolean flag = true;
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).description.contains(taskDes)) {
                    count++;
                    if (flag) {
                        System.out.println("Here are the matching tasks in your list:");
                        flag = false;
                    }
                    if (list.get(i).getType().equals("[T]")) {
                        System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                    } else if (list.get(i).getType().equals("[D]")) {
                        System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description + " (by: " + list.get(i).extra1 + list.get(i).extra2 + " of " + list.get(i).extra3 + " " + list.get(i).extra4 + ", " + list.get(i).extra5 + ":" + list.get(i).extra6 + list.get(i).extra7 + list.get(i).extra8 + ")");
                    } else if (list.get(i).getType().equals("[E]")) {
                        System.out.println(list.get(i).getType() + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description + " (at: " + list.get(i).extra1 + list.get(i).extra2 + " of " + list.get(i).extra3 + " " + list.get(i).extra4 + ", " + list.get(i).extra5 + ":" + list.get(i).extra6 + list.get(i).extra7 + list.get(i).extra8 + ")");
                    }
                }
            }
            if (count == 0) {
                System.out.println("☹ OOPS!!! I'm sorry, but there are no matching tasks in your list.");
            }
        } catch (dukeException e) {
            System.out.println("error\n + e");
        }
    }

    public void toRun(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (type.equals("list")) {
            tasks.printList();
        } else if (type.equals("done")) {
            taskDone(tasks.getList(), ui.readDescription());
        } else if (type.equals("delete")) {
            taskDelete(tasks.getList(), ui.readDescription());
        } else if (type.equals("find")) {
            taskFind(tasks.getList(), ui.readDescription());
        } else {
            if (type.equals("todo")) {
                ToDo.addToDo(tasks.getList(), ui.readDescription(), storage);
            } else if (type.equals("deadline")) {
                tasks.addDeadline(tasks.getList(), ui.readDescription(), storage);
            } else if (type.equals("event")) {
                tasks.addEvent(tasks.getList(), ui.readDescription(), storage);
            }
        }
    }
}
