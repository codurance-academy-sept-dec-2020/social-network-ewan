package social_network.services;

import social_network.entities.User;
import social_network.exceptions.NoUserException;
import social_network.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (NoUserException e) {
            return userRepository.create(username);
        }
    }

    public User getUserByID(Long userID) {
        throw new UnsupportedOperationException();
    }
}
