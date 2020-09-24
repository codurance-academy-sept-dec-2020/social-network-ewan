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
import social_network.services.PostService;
import social_network.services.UserService;

import java.io.PrintStream;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class PostingTest {

    @Mock
    private PrintStream output;

    ConsoleClient client;

    @BeforeEach
    void setUp() {
        ConsoleCommandParser commandParser = new ConsoleCommandParser();
        PostService postService = new PostService();
        UserService userService = new UserService();
        ConsoleCommandFactory commandFactory = new ConsoleCommandFactory(postService, userService);
        SocialNetwork socialNetwork = new SocialNetwork();
        client = new ConsoleClient(commandParser, commandFactory, socialNetwork);
    }

    @Test
    void user_can_post_to_wall_and_read() throws UnsupportedCommandException {
        client.execute("Alice -> I love the weather today");
        client.execute("Bob -> Damn! We lost!");
        client.execute("Bob -> Good game though.");


        client.execute("Alice");
        client.execute("Bob");

        InOrder inOrder = inOrder();
        inOrder.verify(output).println("I love the weather today (5 minutes ago)");
        inOrder.verify(output).println("Good game though. (1 minute ago)");
        inOrder.verify(output).println("Damn! We lost! (2 minutes ago)");
    }
}
