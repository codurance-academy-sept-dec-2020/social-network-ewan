package social_network.console_client;

import social_network.commands.Command;
import social_network.commands.PostCommand;
import social_network.commands.ReadCommand;
import social_network.exceptions.UnsupportedCommandException;
import social_network.services.PostService;
import social_network.services.UserService;

public class ConsoleCommandFactory {
    private final PostService postService;
    private final UserService userService;

    public ConsoleCommandFactory(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    public Command create(ConsoleCommand consoleCommand) throws UnsupportedCommandException {
        if (consoleCommand.command.equals("->")) {
            return new PostCommand(postService, userService, consoleCommand.username, consoleCommand.argument);
        }
        if (consoleCommand.command.equals("")) {
            return new ReadCommand();
        }

        throw new UnsupportedCommandException();
    }
}
