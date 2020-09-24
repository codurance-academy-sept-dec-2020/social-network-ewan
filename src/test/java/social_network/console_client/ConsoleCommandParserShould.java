package social_network.console_client;

import org.junit.jupiter.api.Test;
import social_network.exceptions.UnsupportedCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleCommandParserShould {
    @Test
    void split_post_command_into_username_command_and_post() throws UnsupportedCommandException {
        ConsoleCommandParser parser = new ConsoleCommandParser();
        ConsoleCommand consoleCommand = parser.parse("Alice -> I love the weather today");
        assertEquals("Alice", consoleCommand.username);
        assertEquals("->", consoleCommand.command);
        assertEquals("I love the weather today", consoleCommand.argument);
    }
}
