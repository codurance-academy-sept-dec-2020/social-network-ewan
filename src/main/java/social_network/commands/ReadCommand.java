package social_network.commands;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.output.Printer;
import social_network.services.PostService;
import social_network.services.UserService;

import java.util.List;

public class ReadCommand implements Command {
    private final UserService userService;
    private final String username;
    private final PostService postService;
    private final Printer printer;

    public ReadCommand(PostService postService, UserService userService, Printer printer, String username) {
        this.postService = postService;
        this.userService = userService;
        this.printer = printer;
        this.username = username;
    }

    public void execute() {
        User user = userService.getOrCreateUser(username);
        List<Post> posts = postService.getUserPosts(user.id);
        printer.printPosts(posts);
    }
}
