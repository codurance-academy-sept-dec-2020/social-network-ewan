package social_network.repositories;

import social_network.entities.User;
import social_network.exceptions.NoUserException;

public interface UserRepository {
    User findByUsername(String username) throws NoUserException;

    User create(String username);
}
