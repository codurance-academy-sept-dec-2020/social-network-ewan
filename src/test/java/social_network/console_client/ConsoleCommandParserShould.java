package social_network.console_client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import social_network.exceptions.UnsupportedCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleCommandParserShould {

    private ConsoleCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new ConsoleCommandParser();
    }

    @Test
    void split_post_command_into_username_command_and_post() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = parser.parse("Alice -> I love the weather today");
        assertEquals("Alice", consoleCommand.username);
        assertEquals("->", consoleCommand.command);
        assertEquals("I love the weather today", consoleCommand.argument);
    }

    @Test
    void split_read_command_with_just_username() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = parser.parse("Alice");
        assertEquals("Alice", consoleCommand.username);
        assertEquals("", consoleCommand.command);
        assertEquals("", consoleCommand.argument);
    }

    @Test
    void split_follow_command_with_username_command_and_post() throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = parser.parse("Charlie follows Alice");
        assertEquals("Charlie", consoleCommand.username);
        assertEquals("follows", consoleCommand.command);
        assertEquals("Alice", consoleCommand.argument);
    }
}
