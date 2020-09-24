package social_network.output;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeDeltaFormatterShould {
    @Test
    void formats_time_difference_of_less_than_minute_in_seconds() {
        TimeDeltaFormatter timeDeltaFormatter = new TimeDeltaFormatter();
        LocalDateTime date = LocalDateTime.now().minus(2, ChronoUnit.SECONDS);
        String delta = timeDeltaFormatter.format(date);
        assertEquals("2 seconds ago", delta);
    }
}
