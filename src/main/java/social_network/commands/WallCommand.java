package social_network.commands;

import social_network.output.Printer;
import social_network.services.FollowService;
import social_network.services.PostService;
import social_network.services.UserService;

public class WallCommand implements Command {
    private final UserService userService;
    private final FollowService followService;
    private final PostService postService;
    private final Printer printer;
    private final String username;

    public WallCommand(UserService userService, FollowService followService, PostService postService, Printer printer, String username) {
        this.userService = userService;
        this.followService = followService;
        this.postService = postService;
        this.printer = printer;
        this.username = username;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }
}
