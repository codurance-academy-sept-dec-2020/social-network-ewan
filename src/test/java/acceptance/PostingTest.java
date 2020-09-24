package acceptance;

import clients.ConsoleClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
public class PostingTest {

    @Mock
    private PrintStream output;

    @Test
    void user_can_post_to_wall_and_read() {
        ConsoleClient client = new ConsoleClient();
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
