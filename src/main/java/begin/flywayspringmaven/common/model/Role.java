package begin.flywayspringmaven.common.model;

import begin.flywayspringmaven.api.role.dto.RoleDTO;
import begin.flywayspringmaven.common.base.BaseModel;
import begin.flywayspringmaven.support.httpdefault.annotation.dto.CreateRequestClassDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CreateRequestClassDTO(RoleDTO.class)
public class Role extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @Column
    private String type;

//    @OneToMany(mappedBy = "role")
//    private List<User> users;
}
