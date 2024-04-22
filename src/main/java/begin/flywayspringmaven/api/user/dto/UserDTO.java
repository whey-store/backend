package begin.flywayspringmaven.api.user.dto;

import begin.flywayspringmaven.common.model.Role;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.common.vo.JwtToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private  String avatar;
    private  Integer gender;
    private Integer age;
    private Boolean deleted;
    private LocalDateTime modified;
    @JsonIgnore // JsonIgnore : hidden properties password
    private String password;

    @ApiModelProperty(notes = "Return token when sign up", hidden = true)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private JwtToken jwtToken;

    private Object role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.gender = user.getGender();
        this.deleted = user.getDeleted();
        this.modified = user.getModified();
    }

}
