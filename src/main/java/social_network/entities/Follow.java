package social_network.entities;

public class Follow {
    public final long userID;
    public final long followeeID;

    public Follow(long userID, long followeeID) {
        this.userID = userID;
        this.followeeID = followeeID;
    }
}
