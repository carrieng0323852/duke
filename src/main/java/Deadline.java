public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        this.additionalInformation = by;
    }

    public String getType() {
        return ("[D]");
    }
}
