package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.Post;
import social_network.entities.User;
import social_network.entities.Wall;
import social_network.output.Printer;
import social_network.services.FollowService;
import social_network.services.PostService;
import social_network.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WallCommandShould {
    private static final User USER = new User(1, "Charlie");
    private static final User FOLLOWED_USER = new User(2, "Alice");

    @Mock
    private UserService userService;

    @Mock
    private FollowService followService;

    @Mock
    private PostService postService;

    @Mock
    private Printer printer;

    @Test
    void gets_user_id_gets_their_follows_then_posts_of_everyone() {
        WallCommand command = new WallCommand(userService, followService, postService, printer, USER.username);
        Post charliePost = new Post("I'm in New York today! Anyone want to have a coffee?", USER.id, LocalDateTime.now());
        Post alicePost = new Post("I love the weather today", FOLLOWED_USER.id, LocalDateTime.now());
        List<Post> posts = List.of(
                charliePost,
                alicePost
        );
        List<User> users = List.of(USER, FOLLOWED_USER);
        Wall wall = new Wall(posts, users);
        when(userService.getOrCreateUser(USER.username)).thenReturn(USER);
        when(followService.getFollows(USER.id)).thenReturn(List.of(USER.id, FOLLOWED_USER.id));
        when(userService.getUserByID(USER.id)).thenReturn(USER);
        when(userService.getUserByID(FOLLOWED_USER.id)).thenReturn(FOLLOWED_USER);
        when(postService.getUserPosts(USER.id)).thenReturn(List.of(charliePost));
        when(postService.getUserPosts(FOLLOWED_USER.id)).thenReturn(List.of(charliePost));

        command.execute();

        verify(userService).getOrCreateUser(USER.username);
        verify(followService).getFollows(USER.id);
        verify(postService).getUserPosts(USER.id);
        verify(postService).getUserPosts(FOLLOWED_USER.id);
        verify(printer).printWall(any(Wall.class));
    }
}
