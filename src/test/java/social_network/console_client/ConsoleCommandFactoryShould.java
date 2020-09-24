package social_network.console_client;

import org.junit.jupiter.api.Test;
import social_network.commands.Command;
import social_network.commands.PostCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleCommandFactoryShould {
    @Test
    void creates_post_command_with_required_params() {
        ConsoleCommandFactory factory = new ConsoleCommandFactory();
        ConsoleCommand consoleCommand = new ConsoleCommand("->", "Alice", "I love the weather today");
        Command command = factory.create(consoleCommand);
        assertEquals(PostCommand.class, command.getClass());
    }
}
