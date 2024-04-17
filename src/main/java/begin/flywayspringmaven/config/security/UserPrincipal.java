package begin.flywayspringmaven.config.security;

import begin.flywayspringmaven.common.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

@Getter
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private Integer userId;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Integer age;
    private String phone;

    public UserPrincipal(Integer id, String username, String password, String email, Integer age, String phone) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    public static UserPrincipal create(User user) throws UsernameNotFoundException, Exception {
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAge(), user.getPhone());
    }

    public UserPrincipal(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.phone = user.getPhone();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
