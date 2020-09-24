package social_network.repositories;

import social_network.entities.Follow;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFollowRepository implements FollowRepository {
    private List<Follow> follows;

    public InMemoryFollowRepository() {
        follows = new ArrayList<>();
    }

    public void create(long userID, long followeeID) {
        follows.add(new Follow(userID, followeeID));
    }
}
