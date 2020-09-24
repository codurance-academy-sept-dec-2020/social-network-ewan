package social_network.output;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.Post;
import social_network.entities.User;
import social_network.entities.Wall;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Test
    void prints_wall_by_users_with_name() {
        User charlie = new User(1, "Charlie");
        User alice = new User(2, "Alice");
        LocalDateTime twoMinsAgo = LocalDateTime.now().minus(2, ChronoUnit.MINUTES);
        LocalDateTime twoSecsAgo = LocalDateTime.now().minus(2, ChronoUnit.SECONDS);
        LocalDateTime fiveMinsAgo = LocalDateTime.now().minus(5, ChronoUnit.MINUTES);
        when(timeDeltaFormatter.format(twoMinsAgo)).thenReturn("2 minutes ago");
        when(timeDeltaFormatter.format(twoSecsAgo)).thenReturn("2 seconds ago");
        when(timeDeltaFormatter.format(fiveMinsAgo)).thenReturn("5 minutes ago");

        List<Post> posts = List.of(
                new Post("Hello", charlie.id, twoMinsAgo),
                new Post("I'm in New York today! Anyone want to have a coffee?", charlie.id, twoSecsAgo),
                new Post("I love the weather today", alice.id, fiveMinsAgo)
        );

        List<User> users = List.of(charlie, alice);
        Wall wall = new Wall(posts, users);

        printer.printWall(wall);

        verify(output).println("Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)");
        verify(output).println("Charlie - Hello (2 minutes ago)");
        verify(output).println("Alice - I love the weather today (5 minutes ago)");
    }
}
