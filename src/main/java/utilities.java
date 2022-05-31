import java.util.List;
import java.util.Calendar;

public class utilities {
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

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        return new StringBuilder().append(month).append("-").append(year).toString();
    }
}
