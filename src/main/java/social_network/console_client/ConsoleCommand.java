package social_network.console_client;

import social_network.commands.Command;

public class ConsoleCommand {
    public final String command;
    public final String username;
    public final String argument;

    public ConsoleCommand(String command, String username, String argument) {
        this.command = command;
        this.username = username;
        this.argument = argument;
    }

    public Command create(ConsoleCommand command) {
        throw new UnsupportedOperationException();
    }
}
