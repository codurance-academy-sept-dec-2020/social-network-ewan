package social_network.entities;

import java.time.LocalDateTime;

public class Post {
    public final String text;
    public final long userID;
    public final LocalDateTime created_at;

    public Post(String text, long userID, LocalDateTime created_at) {
        this.text = text;
        this.userID = userID;
        this.created_at = created_at;
    }
}
