package social_network.output;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDeltaFormatter {

    private static final int MINUTE = 60;
    private static final long HOUR = MINUTE * 60;

    public String format(LocalDateTime date) {
        long diff = ChronoUnit.SECONDS.between(date, LocalDateTime.now());
        if (diff >= HOUR) {
            return String.format("%s hours ago", diff / HOUR);
        }
        if (diff >= MINUTE) {
            return String.format("%s minutes ago", diff / MINUTE);
        }
        return String.format("%s seconds ago", diff);
    }
}
