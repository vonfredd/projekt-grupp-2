package org.http.backend.service;

import org.http.backend.entity.User;
import org.http.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User find(String id) {
        Optional<User> user = userRepository.findByGoogleId(id);
        return user.orElseThrow(() -> new RuntimeException("No such ID " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
