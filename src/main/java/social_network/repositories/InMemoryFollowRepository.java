package social_network.repositories;

import social_network.entities.Follow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryFollowRepository implements FollowRepository {
    private final List<Follow> follows;

    public InMemoryFollowRepository() {
        follows = new ArrayList<>();
    }

    public void create(long userID, long followeeID) {
        follows.add(new Follow(userID, followeeID));
    }

    public List<Long> getFollowsForUser(long userId) {
        return follows.stream().map(follow -> follow.followeeID).collect(Collectors.toList());
    }

    public void deleteFollows(long userId) {
        follows.removeIf(follow -> follow.userID == userId || follow.followeeID == userId);
    }
}
