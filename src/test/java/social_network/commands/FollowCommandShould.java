package social_network.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.User;
import social_network.services.FollowService;
import social_network.services.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FollowCommandShould {
    private static final User USER = new User(1, "Charlie");
    private static final User SECOND_USER = new User(2, "Alice");

    @Mock
    private UserService userService;

    @Mock
    private FollowService followService;

    @Test
    void get_user_ids_and_save_follow_to_repo() {
        FollowCommand command = new FollowCommand(userService, followService, USER.username, SECOND_USER.username);
        when(userService.getOrCreateUser(USER.username)).thenReturn(USER);
        when(userService.getOrCreateUser(SECOND_USER.username)).thenReturn(SECOND_USER);
        command.execute();

        verify(userService).getOrCreateUser(USER.username);
        verify(userService).getOrCreateUser(SECOND_USER.username);
        verify(followService).addFollow(USER.id, SECOND_USER.id);
    }
}
