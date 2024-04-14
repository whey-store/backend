package begin.flywayspringmaven.api.product.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;

    private Float price;

    private String image;

    private String description;

    private Integer amount;

    private Boolean deleted;

    private Integer storeId;
}
