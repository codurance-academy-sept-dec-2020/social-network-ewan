package social_network.console_client;

import social_network.commands.Command;
import social_network.commands.PostCommand;

public class ConsoleCommandFactory {
    public Command create(ConsoleCommand consoleCommand) {
        return new PostCommand(consoleCommand.username, consoleCommand.argument);
    }
}
