package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.repositories.PostRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceShould {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void create_post_with_post_repository() {
        long userID = 1;
        String post = "I love the weather today";
        postService.create(post, userID);

        verify(postRepository).create(post, userID);
    }
}
