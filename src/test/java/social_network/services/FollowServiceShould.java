package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.repositories.FollowRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FollowServiceShould {
    private static final long USER_ID = 1;
    private static final long FOLLOWED_USER_ID = 2;

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowService service;

    @Test
    void create_follow_in_repository() {
        service.addFollow(USER_ID, FOLLOWED_USER_ID);
    }

    @Test
    void returns_follows_for_a_user_id_from_repo() {
        when(followRepository.getFollowsForUser(USER_ID)).thenReturn(List.of(FOLLOWED_USER_ID));
        List<Long> follows = service.getFollows(USER_ID);

        assertEquals(2, follows.size());
        assertEquals(USER_ID, follows.get(0));
        assertEquals(FOLLOWED_USER_ID, follows.get(1));
        verify(followRepository).getFollowsForUser(USER_ID);
    }

    @Test
    void delete_all_follows_for_user() {
        service.deleteFollows(USER_ID);

        verify(followRepository).deleteFollows(USER_ID);
    }
}
