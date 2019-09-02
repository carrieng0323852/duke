public class Event extends Task {

    public Event(String description, String date, String suffix, String month, String year, int hour, char minute1, char minute2, String amorpm) {
        super(description);
        //this.additionalInformation = at;
        this.extra1 = date;
        this.extra2 = suffix;
        this.extra3 = month;
        this.extra4 = year;
        this.extra5 = hour;
        this.extra6 = minute1;
        this.extra7 = minute2;
        this.extra8 = amorpm;
    }

    public String getType() {
        return ("[E]");
    }
}
