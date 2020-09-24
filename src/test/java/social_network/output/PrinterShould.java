package social_network.output;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.Post;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrinterShould {

    @Mock
    private PrintStream output;

    @Mock
    private TimeDeltaFormatter timeDeltaFormatter;

    @InjectMocks
    private Printer printer;

    @Test
    void prints_posts_by_line_in_output() {
        LocalDateTime createdAt = LocalDateTime.now();
        List<Post> posts = List.of(new Post("I love the weather today", 1, createdAt));
        when(timeDeltaFormatter.format(createdAt)).thenReturn("5 minutes ago");
        printer.printPosts(posts);

        verify(output).println("I love the weather today (5 minutes ago)");
    }
}
