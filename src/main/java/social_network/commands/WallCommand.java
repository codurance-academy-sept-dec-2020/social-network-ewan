package social_network.commands;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.entities.Wall;
import social_network.output.Printer;
import social_network.services.FollowService;
import social_network.services.PostService;
import social_network.services.UserService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        User user = userService.getOrCreateUser(username);
        List<Long> follows = followService.getFollows(user.id);
        List<Post> posts = follows.stream().flatMap(userPostsMap()).collect(Collectors.toList());
        List<User> users = follows.stream().map(userService::getUserByID).collect(Collectors.toList());
        printer.printWall(new Wall(posts, users));
    }

    private Function<Long, Stream<? extends Post>> userPostsMap() {
        return userID -> postService.getUserPosts(userID).stream();
    }
}
