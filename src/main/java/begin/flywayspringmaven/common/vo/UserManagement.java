package begin.flywayspringmaven.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserManagement {

    @ApiModelProperty(notes = "Gender, Set Female: 1, Male: 2, Other: 3", required = true)
    @Min(value = 1, message = "Gender {validation.user-size-min}")
    @Max(value = 3, message = "Gender {validation.user-size-max}")
    private Integer gender;

    @ApiModelProperty(notes = "User Name", required = true)
    private String userName;

    @NotEmpty(message = "Email {validation.not-empty}")
    @ApiModelProperty(notes = "Email", required = true)
    private String email;

    @NotEmpty(message = "{info.password} {validation.not-empty}")
    @ApiModelProperty(notes = "Password(min = 8 and  max = 25)")
    private String password;

    private Integer roleId;
}
