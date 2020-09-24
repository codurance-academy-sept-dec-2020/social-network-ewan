package social_network.entities;

import java.util.List;

public class Wall {
    public final List<Post> posts;
    public final List<User> users;

    public Wall(List<Post> posts, List<User> users) {
        this.posts = posts;
        this.users = users;
    }
}
