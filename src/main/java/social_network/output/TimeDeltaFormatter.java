package social_network.output;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeDeltaFormatter {
    public String format(LocalDateTime date) {
        long diff = ChronoUnit.SECONDS.between(date, LocalDateTime.now());
        return String.format("%s seconds ago", diff);
    }
}
