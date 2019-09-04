import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String extra1;
    protected String extra2;
    protected String extra3;
    protected String extra4;
    protected int extra5;
    protected char extra6;
    protected char extra7;
    protected String extra8;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getType() {
        return ("task");
    }
}