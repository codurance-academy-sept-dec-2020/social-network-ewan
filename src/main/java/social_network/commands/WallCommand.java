package social_network.commands;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.entities.Wall;
import social_network.exceptions.NoUserException;
import social_network.output.Printer;
import social_network.services.FollowService;
import social_network.services.PostService;
import social_network.services.UserService;

import java.util.ArrayList;
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
        List<Post> posts = follows.stream().flatMap(userPostsMap()).sorted((p1, p2) -> p2.created_at.compareTo(p1.created_at)).collect(Collectors.toList());
        List<User> users = mapUsers(follows);
        printer.printWall(new Wall(posts, users));
    }

    private List<User> mapUsers(List<Long> follows) {
        ArrayList<User> users = new ArrayList<>();
        for (Long userID : follows) {
            try {
                users.add(userService.getUserByID(userID));
            } catch (NoUserException e) {
                followService.deleteFollows(userID);
            }
        }
        return users;
    }

    private Function<Long, Stream<? extends Post>> userPostsMap() {
        return userID -> postService.getUserPosts(userID).stream();
    }
}
