package begin.flywayspringmaven.api.role.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoleDTO {
    private Integer id;
    private String name;
    private String type;
}
