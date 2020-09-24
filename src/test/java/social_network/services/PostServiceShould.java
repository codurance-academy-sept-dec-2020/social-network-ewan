package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.Post;
import social_network.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void gets_posts_for_a_user() {
        long userID = 1;
        List<Post> posts = List.of(new Post("I love the weather today", userID, LocalDateTime.now()));
        when(postRepository.getUserPosts(userID)).thenReturn(posts);

        List<Post> userPosts = postService.getUserPosts(userID);
        assertEquals(posts, userPosts);
        verify(postRepository).getUserPosts(userID);
    }
}
