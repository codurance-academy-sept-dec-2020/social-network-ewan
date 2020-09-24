package social_network.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeDeltaFormatterShould {

    private TimeDeltaFormatter timeDeltaFormatter;

    @BeforeEach
    void setUp() {
        timeDeltaFormatter = new TimeDeltaFormatter();
    }

    @Test
    void formats_time_difference_of_less_than_minute_in_seconds() {
        LocalDateTime date = LocalDateTime.now().minus(2, ChronoUnit.SECONDS);
        String delta = timeDeltaFormatter.format(date);
        assertEquals("2 seconds ago", delta);
    }

    @Test
    void formats_time_difference_of_less_than_an_hour_in_minutes() {
        LocalDateTime date = LocalDateTime.now().minus(24, ChronoUnit.MINUTES);
        String delta = timeDeltaFormatter.format(date);
        assertEquals("24 minutes ago", delta);
    }

    @Test
    void formats_time_difference_of_less_than_a_day_in_hours() {
        LocalDateTime date = LocalDateTime.now().minus(4, ChronoUnit.HOURS);
        String delta = timeDeltaFormatter.format(date);
        assertEquals("4 hours ago", delta);
    }
}
