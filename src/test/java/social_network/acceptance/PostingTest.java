package social_network.acceptance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.SocialNetwork;
import social_network.console_client.ConsoleClient;
import social_network.console_client.ConsoleCommandFactory;
import social_network.console_client.ConsoleCommandParser;
import social_network.exceptions.UnsupportedCommandException;
import social_network.output.Printer;
import social_network.output.TimeDeltaFormatter;
import social_network.repositories.*;
import social_network.services.PostService;
import social_network.services.UserService;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostingTest {

    @Mock
    private PrintStream output;

    @Mock
    private DateTimeGenerator dateTimeGenerator;

    ConsoleClient client;

    @BeforeEach
    void setUp() {
        ConsoleCommandParser commandParser = new ConsoleCommandParser();
        PostRepository postRepository = new InMemoryPostRepository(dateTimeGenerator);
        PostService postService = new PostService(postRepository);
        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new UserService(userRepository);
        TimeDeltaFormatter timeDeltaFormatter = new TimeDeltaFormatter();
        Printer printer = new Printer(output, timeDeltaFormatter);
        ConsoleCommandFactory commandFactory = new ConsoleCommandFactory(postService, userService, printer);
        SocialNetwork socialNetwork = new SocialNetwork();
        client = new ConsoleClient(commandParser, commandFactory, socialNetwork);
    }

    @Test
    void user_can_post_to_wall_and_read() throws UnsupportedCommandException {
        when(dateTimeGenerator.timeNow())
                .thenReturn(LocalDateTime.now().minus(5, ChronoUnit.MINUTES))
                .thenReturn(LocalDateTime.now().minus(2, ChronoUnit.MINUTES))
                .thenReturn(LocalDateTime.now().minus(1, ChronoUnit.MINUTES));

        client.execute("Alice -> I love the weather today");
        client.execute("Bob -> Damn! We lost!");
        client.execute("Bob -> Good game though.");


        client.execute("Alice");
        client.execute("Bob");

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).println("I love the weather today (5 minutes ago)");
        inOrder.verify(output).println("Good game though. (1 minute ago)");
        inOrder.verify(output).println("Damn! We lost! (2 minutes ago)");
    }
}
