package social_network.console_client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.SocialNetwork;
import social_network.commands.Command;
import social_network.commands.PostCommand;

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

    @InjectMocks
    private ConsoleClient client;

    @Test
    void parse_command_input_get_command_and_send_to_() {
        String consoleInput = "Alice -> I love the weather today";
        String consoleCommandString = "->";
        social_network.console_client.ConsoleCommand consoleCommand = new social_network.console_client.ConsoleCommand(consoleCommandString);
        Command command = new PostCommand();
        when(commandParser.parse(consoleInput)).thenReturn(consoleCommand);
        when(commandFactory.create(consoleCommand)).thenReturn(command);
        client.execute(consoleInput);

        verify(commandParser).parse(consoleInput);
        verify(commandFactory).create(consoleCommand);
        verify(socialNetwork).run(command);
    }
}
