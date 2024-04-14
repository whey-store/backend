package begin.flywayspringmaven.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * User Request DTO.
 */
@Data
public class UserRequestDTO {
    private String username;
    private Integer roleId;
    private String email;
    private String password;
    private String phone;
    private  String avatar;
    private  Integer gender;
    private Integer age;
    private Boolean deleted;
    private LocalDateTime modified;
}
