package social_network.console_client;

import social_network.exceptions.UnsupportedCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleCommandParser {

    private static final String CONSOLE_COMMAND_REGEX = "^(?<username>[\\w]+)\\s?(?<command>(?:->)?)\\s?(?<argument>[\\s\\S]*)$";

    public ConsoleCommand parse(String consoleInput) throws UnsupportedCommandException {
        Pattern pattern = Pattern.compile(CONSOLE_COMMAND_REGEX);
        Matcher matcher = pattern.matcher(consoleInput);
        if (matcher.matches()) {
            return new ConsoleCommand(
                    matcher.group("command"), matcher.group("username"), matcher.group("argument")
            );
        }

        throw new UnsupportedCommandException();
    }
}
