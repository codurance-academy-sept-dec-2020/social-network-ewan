package social_network.services;

import social_network.entities.Post;
import social_network.repositories.PostRepository;

import java.util.List;

public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(String post, long userID) {
        postRepository.create(post, userID);
    }

    public List<Post> getUserPosts(long userID) {
        return postRepository.getUserPosts(userID);
    }
}
