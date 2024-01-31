package begin.flywayspringmaven.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private  String avatar;
    private  Integer gender;
    private Integer age;
    private LocalDateTime modified;

    public UserDTO(Integer id, String name, String email, Integer gender, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.modified = modified;
    }
}
