package begin.flywayspringmaven.common.model;

import begin.flywayspringmaven.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Voucher extends BaseModel {
    private String name;
    private Float percentReduction;
    private Integer quantity;
    private LocalDateTime start_day;
    private LocalDateTime end_day;
    private Boolean deleted;

    @ManyToMany
    @JoinTable(
        name = "user_voucher",
        joinColumns = @JoinColumn(name = "voucher_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
}
