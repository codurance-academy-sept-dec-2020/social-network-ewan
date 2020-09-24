package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.entities.User;
import social_network.exceptions.NoUserException;
import social_network.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceShould {
    private static final User USER = new User(1, "Alice");

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;

    @Test
    void return_new_user_from_repo_if_does_not_exist() throws NoUserException {
        when(userRepository.findByUsername(USER.username)).thenThrow(NoUserException.class);
        when(userRepository.create(USER.username)).thenReturn(USER);
        User user = service.getOrCreateUser(USER.username);

        assertEquals(USER, user);

        verify(userRepository).findByUsername(USER.username);
        verify(userRepository).create(USER.username);
    }

    @Test
    void returns_user_for_username_if_exists() throws NoUserException {
        when(userRepository.findByUsername(USER.username)).thenReturn(USER);
        User user = service.getOrCreateUser(USER.username);

        assertEquals(USER, user);
        verify(userRepository).findByUsername(USER.username);
        verify(userRepository, never()).create(USER.username);
    }

    @Test
    void fetches_user_by_id() throws NoUserException {
        when(userRepository.findByID(USER.id)).thenReturn(USER);
        User user = service.getUserByID(USER.id);

        assertEquals(USER, user);
        verify(userRepository).findByID(USER.id);
    }
}
