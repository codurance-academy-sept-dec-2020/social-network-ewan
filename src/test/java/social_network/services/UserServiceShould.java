package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.User;
import social_network.exceptions.NoUserException;
import social_network.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceShould {
    private static final User USER = new User(1, "Alice");

    @Mock
    private UserRepository userRepository;

    @Test
    void return_new_user_from_repo_if_does_not_exist() throws NoUserException {
        UserService service = new UserService(userRepository);
        when(userRepository.findByUsername(USER.username)).thenThrow(NoUserException.class);
        when(userRepository.create(USER.username)).thenReturn(USER);
        User user = service.getOrCreateUser(USER.username);

        assertEquals(USER, user);

        verify(userRepository).findByUsername(USER.username);
        verify(userRepository).create(USER.username);
    }
}
