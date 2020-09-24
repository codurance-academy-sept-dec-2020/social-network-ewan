package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.User;
import social_network.services.PostService;
import social_network.services.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostCommandShould {

    private static final User USER = new User(1, "Alice");

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Test
    void get_user_from_service_and_create_post_with_post_service() {
        String username = "Alice";
        String post = "I love the weather today";
        PostCommand command = new PostCommand(postService, userService, username, post);
        when(userService.getOrCreateUser(username)).thenReturn(USER);
        command.execute();

        verify(userService).getOrCreateUser(username);
        verify(postService).create(USER.id, post);
    }
}
