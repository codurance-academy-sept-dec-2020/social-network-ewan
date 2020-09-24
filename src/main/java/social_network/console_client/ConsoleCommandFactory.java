package social_network.console_client;

import social_network.commands.Command;
import social_network.commands.FollowCommand;
import social_network.commands.PostCommand;
import social_network.commands.ReadCommand;
import social_network.exceptions.UnsupportedCommandException;
import social_network.output.Printer;
import social_network.services.PostService;
import social_network.services.UserService;

public class ConsoleCommandFactory {
    private final PostService postService;
    private final UserService userService;
    private final Printer printer;

    public ConsoleCommandFactory(PostService postService, UserService userService, Printer printer) {
        this.postService = postService;
        this.userService = userService;
        this.printer = printer;
    }

    public Command create(ConsoleCommand consoleCommand) throws UnsupportedCommandException {
        if (consoleCommand.command.equals("->")) {
            return new PostCommand(postService, userService, consoleCommand.username, consoleCommand.argument);
        }
        if (consoleCommand.command.equals("")) {
            return new ReadCommand(postService, userService, printer, consoleCommand.username);
        }
        if (consoleCommand.command.equals("follows")) {
            return new FollowCommand();
        }

        throw new UnsupportedCommandException();
    }
}
