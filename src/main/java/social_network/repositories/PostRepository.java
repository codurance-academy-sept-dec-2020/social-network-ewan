package social_network.repositories;

import social_network.entities.Post;

import java.util.List;

public interface PostRepository {
    void create(String post, long userID);

    List<Post> getUserPosts(long userID);
}
