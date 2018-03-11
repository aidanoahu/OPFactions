package network.ethyl.opfactions.utils;

public class Time {

    /*

    Utility class to convert time values

     */

    public static String convertMillis(long ms, boolean shortver) {
        long second = (ms / 1000) % 60;
        long minute = (ms / (1000 * 60)) % 60;
        long hour = (ms / (1000 * 60 * 60)) % 24;
        long days = (ms / (1000 * 60 * 60 * 24));

        StringBuilder time = new StringBuilder();

        //String time = String.format("%02d hours %02d minutes %02d seconds", hour, minute, second, ms);

        if (days > 0) {
            if (shortver) {
                time.append(days + "d");
            } else {
                time.append(days + " days, ");
            }
        }
        if (hour > 0) {
            if (shortver) {
                time.append(hour + "h");
            } else {
                time.append(hour + " hours, ");
            }
        }
        if (minute > 0) {
            if (shortver) {
                time.append(minute + "m");
            } else {
                time.append(minute + " minutes, ");
            }
        }
        if (second > 0) {
            if (shortver) {
                time.append(second + "s");
            } else {
                time.append(second + " seconds, ");
            }
        }

        if (!shortver) {
            time.setLength(time.length() - 2);
        }

        return time.toString();
    }
}
