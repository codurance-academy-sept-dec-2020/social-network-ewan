package social_network.console_client;

import social_network.SocialNetwork;
import social_network.commands.Command;
import social_network.exceptions.UnsupportedCommandException;

public class ConsoleClient {
    private ConsoleCommandParser commandParser;
    private ConsoleCommandFactory commandFactory;
    private SocialNetwork socialNetwork;

    public ConsoleClient(ConsoleCommandParser commandParser, ConsoleCommandFactory commandFactory, SocialNetwork socialNetwork) {
        this.commandParser = commandParser;
        this.commandFactory = commandFactory;
        this.socialNetwork = socialNetwork;
    }

    public void execute(String input) throws UnsupportedCommandException {
        ConsoleCommand consoleCommand = commandParser.parse(input);
        Command command = commandFactory.create(consoleCommand);
        socialNetwork.run(command);
    }
}
