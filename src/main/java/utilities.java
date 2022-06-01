import java.util.List;
import java.util.Calendar;

public class utilities {

    private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**
     * Print lines by string list. 
     * @param sout_strs
     */
    public static void printLines(List<String> sout_strs) {
        for (String str : sout_strs) {
            System.out.println(str);
        }
    }

    /**
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return current date by MM-YYYY
     * @return
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        return new StringBuilder().append(month).append("-").append(year).toString();
    }

    /**
     * Verify input string is valid date format(MM/DD). 
     * @param text
     * @return
     */
    public static boolean isValidDate(String text) {
        String[] strings = text.split("/");
        int month;
        int day;
        // Check text's format with MM/DD
        if (strings.length != 2) {
            return false;
        }
        // Check MM & DD is numeric
        try {
            month = Integer.parseInt(strings[0]);
            day = Integer.parseInt(strings[1]);
        } catch (Exception e) {
            return false;
        }
        // Check MM & DD
        if (month > 12 || month < 0 || day < 0 || day > days[month]) {
            return false;
        }
        return true;
    }

    /**
     * Use given date and stay days to return reserve date list. 
     * @param date
     * @param numberOfNight
     * @return String array stored reserve dates. 
     */
    public static String[] calculateDate(String date, String numberOfNight) {
        // Split input date into MM & DD
        String[] strings = date.split("/");
        int month = Integer.parseInt(strings[0]);
        int day = Integer.parseInt(strings[1]);
        int staydays = Integer.parseInt(numberOfNight);
        // Create a string array to store reserve date. 
        String[] reserveDates = new String[staydays];
        for (int i = 0; i < staydays; i++) {
            reserveDates[i] = new StringBuilder().append(String.valueOf(month)).append("/").append(String.valueOf(day)).toString();
            day ++;
            // Operating across a month
            if (day > days[month]) {
                day = 1;
                month++;
                // Operating across a year
                if (month > 12) {
                    month = 1;
                }
            }
        }
        return reserveDates;
    }
}
