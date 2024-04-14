package begin.flywayspringmaven.config;

import begin.flywayspringmaven.api.auth.AuthService;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.config.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = new User();

        user = this.authService.findUserByUserId(Integer.parseInt(userId)).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));

        UserDetails userDetails = null;
        try {
            userDetails = UserPrincipal.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDetails;
    }
}
