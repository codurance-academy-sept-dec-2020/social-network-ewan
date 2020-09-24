package social_network.repositories;

import social_network.entities.Post;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPostRepository implements PostRepository {
    private List<Post> posts;

    public InMemoryPostRepository() {
        posts = new ArrayList<>();
    }

    public void create(String post, long userID) {
        posts.add(new Post(post, userID));
    }
}
