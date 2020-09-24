package social_network.output;

import social_network.entities.Post;
import social_network.entities.User;
import social_network.entities.Wall;

import java.io.PrintStream;
import java.util.List;

public class Printer {
    private static final String POST_FORMAT = "%s (%s)";
    private static final String WALL_FORMAT = "%s - %s (%s)";
    private PrintStream output;
    private final TimeDeltaFormatter timeDeltaFormatter;

    public Printer(PrintStream output, TimeDeltaFormatter timeDeltaFormatter) {
        this.output = output;
        this.timeDeltaFormatter = timeDeltaFormatter;
    }

    public void printPosts(List<Post> posts) {
        for (Post post : posts) {
            String postLine = postFormat(post);
            output.println(postLine);
        }
    }

    private String postFormat(Post post) {
        return String.format(POST_FORMAT, post.text, timeDeltaFormatter.format(post.created_at));
    }

    public void printWall(Wall wall) {
        for (Post post : wall.posts) {
            String wallLine = wallFormat(post, wall.users);
            System.out.println(wallLine);
            output.println(wallLine);
        }
    }

    private String wallFormat(Post post, List<User> users) {
        String username = users.stream().filter(u -> u.id == post.userID).findAny().orElse(anonymousUser()).username;
        return String.format(WALL_FORMAT, username, post.text, timeDeltaFormatter.format(post.created_at));
    }

    private User anonymousUser() {
        return new User(0, "Anon");
    }
}
