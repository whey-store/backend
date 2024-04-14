package begin.flywayspringmaven.common.model;

import begin.flywayspringmaven.common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
public class Product extends BaseModel {
    private static final long serialVersionUID = 1L;

    @Size(max = 50, message = "Name {validation.size-max}")
    private String name;

    private Float price;

    @Size(max = 255, message = "Avatar {validation.size-max}")
    private String image;

    @Size(max = 255, message = "Avatar {validation.size-max}")
    private String description;

    private Integer amount;

    private Boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    public Product() {

    }
}
