package social_network.commands;

import social_network.entities.User;
import social_network.services.FollowService;
import social_network.services.UserService;

public class FollowCommand implements Command {
    private final UserService userService;
    private final String username;
    private final String followeeName;
    private final FollowService followService;

    public FollowCommand(UserService userService, FollowService followService, String username, String followeeName) {
        this.userService = userService;
        this.followService = followService;
        this.username = username;
        this.followeeName = followeeName;
    }

    public void execute() {
        User user = userService.getOrCreateUser(username);
        User followee = userService.getOrCreateUser(followeeName);
        followService.addFollow(user.id, followee.id);
    }
}
