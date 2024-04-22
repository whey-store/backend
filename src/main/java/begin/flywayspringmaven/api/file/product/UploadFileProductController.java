package begin.flywayspringmaven.api.file.product;


import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.exception.FileInValidException;
import begin.flywayspringmaven.exception.FileUploadException;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class UploadFileProductController {

    private final UploadFileProductService uploadFileProductService;

    public UploadFileProductController(UploadFileProductService uploadFileProductService) {
        this.uploadFileProductService = uploadFileProductService;
    }

    @PostMapping("/products/upload/{productId}")
    public APIResponse<ProductDTO> uploadImageProduct(
        @PathVariable(value = "productId", required = true) int productId,
        @RequestParam(value = "file", required = true) MultipartFile multipartFile
    ) throws FileInValidException, FileUploadException, IOException, NotFoundException {
        return APIResponse.okStatus(uploadFileProductService.uploadImageProduct(productId, multipartFile));
    }
}
