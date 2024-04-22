package begin.flywayspringmaven.api.product;

import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.api.product.dto.ProductRequestDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.vo.PageInfo;
import begin.flywayspringmaven.exception.BadRequestException;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    /**
     * Get list product by admin
     * @param query
     * @param page
     * @param limit
     * @param sort
     * @return Oioilokkk
     */
    @GetMapping("/products")
    public APIResponse<PageInfo<ProductDTO>> getProductList(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "limit", required = false) Integer limit,
        @RequestParam(value = "sort", required = true) String sort
    ) throws Exception {
        return APIResponse.okStatus(this.productService.getProductAdminList(query, page, limit, sort));
    }

    /**
     * Create product
     *
     * @param productRequestDTO
     * @return
     * @throws NotFoundException
     * @throws BadRequestException
     * @throws IOException
     */
    @PostMapping("/products")
    public APIResponse<ProductDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) throws NotFoundException, BadRequestException, IOException {
        return APIResponse.okStatus(this.productService.createProduct(productRequestDTO));
    }
}

