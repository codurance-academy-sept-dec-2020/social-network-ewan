package social_network.repositories;

import social_network.entities.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {
    private final List<Post> posts;
    private DateTimeGenerator dateTimeGenerator;

    public InMemoryPostRepository(DateTimeGenerator dateTimeGenerator) {
        this.dateTimeGenerator = dateTimeGenerator;
        posts = new ArrayList<>();
    }

    public void create(String post, long userID) {
        posts.add(new Post(post, userID, dateTimeGenerator.timeNow()));
    }

    public List<Post> getUserPosts(long userID) {
        return posts.stream().filter(byUserID(userID))
                .sorted(reverseChronologicalOrder()).collect(Collectors.toList());
    }

    private Comparator<Post> reverseChronologicalOrder() {
        return (p1, p2) -> p2.created_at.compareTo(p1.created_at);
    }

    private Predicate<Post> byUserID(long userID) {
        return p -> p.userID == userID;
    }
}
