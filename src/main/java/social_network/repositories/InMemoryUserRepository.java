package social_network.repositories;

import social_network.entities.User;
import social_network.exceptions.NoUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users;
    private int currentID = 1;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }

    public User findByUsername(String username) throws NoUserException {
        Optional<User> optionalUser = users.stream().filter(u -> u.username.equals(username)).findAny();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoUserException();
    }

    public User create(String username) {
        User user = new User(getNextID(), username);
        users.add(user);
        return user;
    }

    private long getNextID() {
        return currentID++;
    }
}
