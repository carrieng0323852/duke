import java.text.DateFormatSymbols;

public class Time {

    public static String getsuffix(int x) {
        if (x >= 11 && x <= 13) {
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

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public static int getHours(String str) {
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

    public static String convert12(String str) {
        int h1 = (int) str.charAt(0) - '0';
        int h2 = (int) str.charAt(1) - '0';
        int hh = (h1 * 10) + h2;

        if (hh < 12) {
            return "am";
        } else {
            return "pm";
        }
    }
}
