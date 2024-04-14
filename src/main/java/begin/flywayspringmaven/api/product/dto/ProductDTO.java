package begin.flywayspringmaven.api.product.dto;

import begin.flywayspringmaven.common.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String name;
    private Float price;
    private String image;
    private String description;
    private Integer amount;
    private Boolean deleted;
    private Integer storeId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.amount = product.getAmount();
    }
}
