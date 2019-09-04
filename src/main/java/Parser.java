public class Parser {
    private Storage storage;
    private Ui ui;

    public Parser(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    public Command parse(String ab) {
        return new Command(storage, ui, ab);
    }
}
