package social_network.services;

import social_network.repositories.FollowRepository;

import java.util.ArrayList;
import java.util.List;

public class FollowService {
    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public void addFollow(long userID, long followeeID) {
        followRepository.create(userID, followeeID);
    }

    public List<Long> getFollows(long userID) {
        ArrayList<Long> follows = new ArrayList<>();
        follows.add(userID);
        follows.addAll(followRepository.getFollowsForUser(userID));
        return follows;
    }

    public void deleteFollows(Long userID) {
        followRepository.deleteFollows(userID);
    }
}
