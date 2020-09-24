package social_network.output;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDeltaFormatter {

    private static final int MINUTE = 60;
    private static final long HOUR = MINUTE * 60;
    private static final long DAY = HOUR * 24;

    public String format(LocalDateTime date) {
        long diffInSeconds = ChronoUnit.SECONDS.between(date, LocalDateTime.now());

        for (Unit unit : Unit.values()) {
            if (diffInSeconds >= unit.seconds) {
                return String.format("%s %s ago", diffInSeconds / unit.seconds, formattedUnitName(diffInSeconds, unit));
            }
        }

        return "just now";
    }

    private String formattedUnitName(long diffInSeconds, Unit unit) {
        String unitName = unit.name;
        if (diffInSeconds / unit.seconds > 1) {
            unitName += "s";
        }
        return unitName;
    }

    private enum Unit {
        DAY(60 * 60 * 24, "day"),
        HOUR(60 * 60, "hour"),
        MINUTE(60, "minute"),
        SECOND(1, "second");

        public final int seconds;
        public final String name;

        Unit(int seconds, String name) {
            this.seconds = seconds;
            this.name = name;
        }
    }
}
