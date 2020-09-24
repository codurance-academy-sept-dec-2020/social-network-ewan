package social_network.repositories;

import social_network.entities.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {
    private List<Post> posts;

    public InMemoryPostRepository() {
        posts = new ArrayList<>();
    }

    public void create(String post, long userID) {
        posts.add(new Post(post, userID));
    }

    public List<Post> getUserPosts(long userID) {
        return posts.stream().filter(p -> p.userID == userID).collect(Collectors.toList());
    }
}
