package social_network.entities;

public class Post {
    public final String text;
    public final long userID;

    public Post(String text, long userID) {
        this.text = text;
        this.userID = userID;
    }
}
