package begin.flywayspringmaven.api.file.product;

import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.common.repository.ProductRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.common.service.aws.FileService;
import begin.flywayspringmaven.exception.FileInValidException;
import begin.flywayspringmaven.exception.FileUploadException;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UploadFileProductService extends BaseService {
    private final ProductRepository productRepository;
    private final FileService fileService;
    private final MessageService messageService;

    public UploadFileProductService(ProductRepository productRepository,
                                    FileService fileService,
                                    MessageService messageService) {
        this.productRepository = productRepository;
        this.fileService = fileService;
        this.messageService = messageService;
    }

    public ProductDTO uploadImageProduct(Integer productId, MultipartFile multipartFile) throws FileInValidException, FileUploadException, IOException , NotFoundException{
        Product product = this.getProductById(productId);
        if(product == null) {
            throw new NotFoundException(NotFoundException.ERROR_PRODUCT_NOT_FOUND,
                this.messageService.buildMessages("error.msg.not-found", "info.product")
            );
        }

        boolean isValidFile = isValidFile(multipartFile);

        if (!isValidFile) {
            throw new FileInValidException(FileInValidException.ERROR_FILE_IN_VALID, "File is inValid");
        }

        String productURL = "";
        URL s3URL = fileService.uploadFile(multipartFile);
        productURL = s3URL.toString();
        product.setImage(productURL);
        productRepository.save(product);
        ProductDTO saveProduct = new ProductDTO(product);
        return saveProduct;
    }

    private boolean isValidFile(MultipartFile multipartFile){
        if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null ){
            return false;
        }
        List<String> allowedFileExtensions = new ArrayList<>(Arrays.asList("pdf", "txt", "epub", "csv", "png", "jpg", "jpeg", "srt"));
        String[] file = multipartFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");
        if(!allowedFileExtensions.contains(file[1])) {
            return false;
        }
        return true;
    }

}
