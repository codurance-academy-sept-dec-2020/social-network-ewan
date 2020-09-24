package social_network.services;

import social_network.repositories.FollowRepository;

import java.util.List;

public class FollowService {
    private FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public void addFollow(long userID, long followeeID) {
        followRepository.create(userID, followeeID);
    }

    public List<Long> getFollows(long id) {
        throw new UnsupportedOperationException();
    }
}
