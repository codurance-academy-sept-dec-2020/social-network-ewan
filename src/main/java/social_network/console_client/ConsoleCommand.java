package social_network.console_client;

import social_network.commands.Command;

public class ConsoleCommand {
    public final String command;

    public ConsoleCommand(String command) {
        this.command = command;
    }

    public Command create(ConsoleCommand command) {
        throw new UnsupportedOperationException();
    }
}
