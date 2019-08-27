public class Event extends Task {

    public Event(String description, String at) {
        super(description);
        this.additionalInformation = at;
    }

    public String getType() {
        return ("[E]");
    }
}
