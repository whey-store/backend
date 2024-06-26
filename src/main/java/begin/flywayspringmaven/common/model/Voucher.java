package begin.flywayspringmaven.common.model;

import begin.flywayspringmaven.common.base.BaseModel;
import begin.flywayspringmaven.support.httpdefault.MapField;
import begin.flywayspringmaven.support.httpdefault.converter.DateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Voucher extends BaseModel {
    private String name;
    private Float percentReduction;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @MapField(converter = DateTimeConverter.class)
    private LocalDateTime startDay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @MapField(converter = DateTimeConverter.class)
    private LocalDateTime endDay;

    @JsonIgnore
    private Boolean deleted;

    @JsonIgnore
    @ManyToMany(mappedBy = "vouchers" , cascade = { CascadeType.ALL})
    private List<User> users;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

}
