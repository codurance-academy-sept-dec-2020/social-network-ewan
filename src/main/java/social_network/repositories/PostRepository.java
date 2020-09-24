package social_network.repositories;

public interface PostRepository {
    void create(String post, long userID);
}
