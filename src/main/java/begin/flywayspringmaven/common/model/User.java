package begin.flywayspringmaven.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.base.BaseModel;
import begin.flywayspringmaven.support.httpdefault.annotation.dto.CreateRequestClassDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@CreateRequestClassDTO(UserDTO.class)
public class User extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Size(max = 50, message = "Name {validation.size-max}")
    private String name;

    @Size(max = 150, message = "Email {validation.size-max}")
    @Email(message = "Email {validation.invalid.email}")
    private String email;

    @Size(max = 15, message = "Phone {validation.size-max}")
    private String phone;

    @Size(max = 255, message = "Avatar {validation.size-max}")
    private String avatar;

    private Integer gender;

    private Integer age;

    public User() {}

    public User(Integer id) {
        this.id = id;
    }
}
