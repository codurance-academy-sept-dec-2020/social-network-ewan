package social_network.repositories;

import java.util.List;

public interface FollowRepository {
    void create(long userID, long followeeID);

    List<Long> getFollowsForUser(long userId);
}
