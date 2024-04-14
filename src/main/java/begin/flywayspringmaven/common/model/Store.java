package begin.flywayspringmaven.common.model;

import begin.flywayspringmaven.common.base.BaseModel;
import begin.flywayspringmaven.support.httpdefault.MapField;
import begin.flywayspringmaven.support.httpdefault.converter.DateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class Store extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Size(max = 50, message = "Name {validation.size-max}")
    private String name;

    @Size(max = 255, message = "Avatar {validation.size-max}")
    private String avatar;

    @Size(max = 255, message = "Phone {validation.size-max}")
    private String address;

    @Size(max = 255, message = "Phone {validation.size-max}")
    private String description;

    @CreationTimestamp
    @JsonIgnore
    @MapField(converter = DateTimeConverter.class)
    public LocalDateTime openTime;

    @CreationTimestamp
    @JsonIgnore
    @MapField(converter = DateTimeConverter.class)
    public LocalDateTime closeTime;

    @Size(max = 50, message = "Name {validation.size-max}")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    public Store() {

    }
}
