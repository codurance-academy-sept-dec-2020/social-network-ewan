package social_network.repositories;

import social_network.entities.User;
import social_network.exceptions.NoUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users;
    private int currentID = 1;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }

    public User findByUsername(String username) throws NoUserException {
        Optional<User> optionalUser = users.stream().filter(byUsername(username)).findAny();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoUserException();
    }

    private Predicate<User> byUsername(String username) {
        return u -> u.username.equals(username);
    }

    public User create(String username) {
        User user = new User(getNextID(), username);
        users.add(user);
        return user;
    }

    public User findByID(long userID) throws NoUserException {
        Optional<User> optionalUser = users.stream().filter(byUserId(userID)).findAny();
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoUserException();
    }

    private Predicate<User> byUserId(long userID) {
        return u -> u.id == userID;
    }

    private long getNextID() {
        return currentID++;
    }
}
