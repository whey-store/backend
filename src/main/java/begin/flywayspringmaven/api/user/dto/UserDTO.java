package begin.flywayspringmaven.api.user.dto;

import begin.flywayspringmaven.common.model.User;
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

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.gender = user.getGender();
        this.modified = user.getModified();
    }
}
