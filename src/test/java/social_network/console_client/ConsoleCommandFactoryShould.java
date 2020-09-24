package social_network.console_client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.commands.*;
import social_network.exceptions.UnsupportedCommandException;
import social_network.services.PostService;
import social_network.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class ConsoleCommandFactoryShould {
    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConsoleCommandFactory factory;

    @Test
    void creates_post_command() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = new ConsoleCommand("->", "Alice", "I love the weather today");
        Command command = factory.create(consoleCommand);
        assertEquals(PostCommand.class, command.getClass());
    }

    @Test
    void creates_read_command() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = new ConsoleCommand("", "Alice", "");
        Command command = factory.create(consoleCommand);
        assertEquals(ReadCommand.class, command.getClass());
    }

    @Test
    void creates_follow_command() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = new ConsoleCommand("follows", "Charlie", "Alice");
        Command command = factory.create(consoleCommand);
        assertEquals(FollowCommand.class, command.getClass());
    }

    @Test
    void creates_wall_command() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = new ConsoleCommand("wall", "Charlie", "");
        Command command = factory.create(consoleCommand);
        assertEquals(WallCommand.class, command.getClass());
    }
}
