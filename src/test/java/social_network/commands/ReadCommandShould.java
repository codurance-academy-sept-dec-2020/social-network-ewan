package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.Post;
import social_network.entities.User;
import social_network.output.Printer;
import social_network.services.PostService;
import social_network.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReadCommandShould {
    private static final User USER = new User(1, "Alice");

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private Printer printer;

    @Test
    void get_user_get_their_posts_and_send_them_printer() {
        ReadCommand command = new ReadCommand(postService, userService, printer, USER.username);
        List<Post> posts = List.of(
                new Post("I love the weather today", USER.id, LocalDateTime.now()),
                new Post("Hello world", USER.id, LocalDateTime.now())
        );
        when(userService.getOrCreateUser(USER.username)).thenReturn(USER);
        when(postService.getUserPosts(USER.id)).thenReturn(posts);

        command.execute();

        verify(userService).getOrCreateUser(USER.username);
        verify(postService).getUserPosts(USER.id);
        verify(printer).printPosts(posts);
    }
}
