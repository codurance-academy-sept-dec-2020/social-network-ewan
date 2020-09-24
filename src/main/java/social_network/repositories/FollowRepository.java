package social_network.repositories;

public interface FollowRepository {
    void create(long userID, long followeeID);
}
