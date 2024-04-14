package begin.flywayspringmaven.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class JwtToken {

    private String accessToken;
    private String tokenType;
    private String refreshToken;

    public JwtToken() {}
}
