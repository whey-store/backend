package begin.flywayspringmaven.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
    private String name;
    private String email;
    private String phone;
    private  String avatar;
    private  Integer gender;
    private Integer age;
}
