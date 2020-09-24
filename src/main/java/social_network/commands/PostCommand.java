package social_network.commands;

import social_network.entities.User;
import social_network.services.PostService;
import social_network.services.UserService;

public class PostCommand implements Command {
    private final UserService userService;
    private final PostService postService;
    private final String username;
    private final String post;

    public PostCommand(PostService postService, UserService userService, String username, String post) {
        this.postService = postService;
        this.userService = userService;
        this.username = username;
        this.post = post;
    }

    public void execute() {
        User user = userService.getOrCreateUser(username);
        postService.create(post, user.id);
    }
}
