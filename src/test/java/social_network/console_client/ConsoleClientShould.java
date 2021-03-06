package social_network.console_client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.SocialNetwork;
import social_network.commands.Command;
import social_network.commands.PostCommand;
import social_network.exceptions.UnsupportedCommandException;
import social_network.services.PostService;
import social_network.services.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsoleClientShould {

    @Mock
    private ConsoleCommandParser commandParser;

    @Mock
    private ConsoleCommandFactory commandFactory;

    @Mock
    private SocialNetwork socialNetwork;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConsoleClient client;

    @Test
    void parse_command_input_get_command_and_send_to_() throws UnsupportedCommandException {
        String consoleInput = "Alice -> I love the weather today";
        ConsoleCommand consoleCommand = new ConsoleCommand(
                "->", "Alice", "I love the weather today"
        );
        Command command = new PostCommand(postService, userService, consoleCommand.username, consoleCommand.argument);
        when(commandParser.parse(consoleInput)).thenReturn(consoleCommand);
        when(commandFactory.create(consoleCommand)).thenReturn(command);
        client.execute(consoleInput);

        verify(commandParser).parse(consoleInput);
        verify(commandFactory).create(consoleCommand);
        verify(socialNetwork).run(command);
    }
}
