package sot.thanasis.dmusers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findUsersByUsernameContaining(String searchString) {
        return userRepository.findByUsernameContaining(searchString).orElse(Collections.emptyList());
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
