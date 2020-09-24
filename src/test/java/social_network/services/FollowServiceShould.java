package social_network.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.repositories.FollowRepository;

@ExtendWith(MockitoExtension.class)
public class FollowServiceShould {

    @Mock
    private FollowRepository followRepository;

    @Test
    void create_follow_in_repository() {
        FollowService service = new FollowService(followRepository);
        long userID = 1;
        long followeeUserID = 1;
        service.addFollow(userID, followeeUserID);
    }
}
