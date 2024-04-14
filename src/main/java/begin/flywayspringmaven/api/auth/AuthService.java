package begin.flywayspringmaven.api.auth;

import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find user by email
     *
     * @param email
     * @return
     */
    public Optional<User> findUserByEmailLogin(String email) {
        return userRepository.findUserByEmailLogin(email);
    }

    /**
     * Find {@link User} by userId
     *
     * @param userId id
     * @return {@link User} type {@link Optional}
     */
    public Optional<User> findUserByUserId(int userId) {
        return userRepository.findById(userId);
    };
}
