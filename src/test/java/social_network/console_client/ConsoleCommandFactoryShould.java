package social_network.console_client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.commands.Command;
import social_network.commands.PostCommand;
import social_network.services.PostService;
import social_network.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
public class ConsoleCommandFactoryShould {
    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Test
    void creates_post_command_with_required_params() {
        ConsoleCommandFactory factory = new ConsoleCommandFactory(postService, userService);
        ConsoleCommand consoleCommand = new ConsoleCommand("->", "Alice", "I love the weather today");
        Command command = factory.create(consoleCommand);
        assertEquals(PostCommand.class, command.getClass());
    }
}
