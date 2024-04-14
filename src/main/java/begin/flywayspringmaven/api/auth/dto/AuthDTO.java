package begin.flywayspringmaven.api.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthDTO {
    private String username;
    private String password;
    private boolean rememberMe;

    private Integer type = 2;
    @JsonIgnore
    public boolean isAdmin() {
        return this.type == 0;
    }

    @JsonIgnore
    public boolean isShop() {
        return this.type == 1;
    }

    @JsonIgnore
    public boolean isUser() {
        return this.type == 2;
    }
}
