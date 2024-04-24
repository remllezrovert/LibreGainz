package org.LibreGainz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Time;

public class TimeConversion {

    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d+)\\s*([hms])");

    public static Time convertToSqlTime(String input) {
        int hours = 0, minutes = 0, seconds = 0;

        Matcher matcher = TIME_PATTERN.matcher(input);
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);
            switch (unit) {
                case "h":
                    hours = value;
                    break;
                case "m":
                    minutes = value;
                    break;
                case "s":
                    seconds = value;
                    break;
            }
        }

        return Time.valueOf(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
