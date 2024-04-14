package begin.flywayspringmaven.api.product;

import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.api.product.dto.ProductRequestDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    @ApiOperation(value = "Create products")
    @Transactional
    public APIResponse<ProductDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws NotFoundException {
        return APIResponse.createdStatus(productService.createProduct(productRequestDTO));
    }
}

