package social_network.services;

import social_network.repositories.PostRepository;

public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(String post, long userID) {
        postRepository.create(post, userID);
    }
}
