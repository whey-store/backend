package begin.flywayspringmaven.api.product;

import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.api.product.dto.ProductRequestDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.repository.ProductRepository;
import begin.flywayspringmaven.common.repository.StoreRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final MessageService messageService;
    public ProductService(ProductRepository productRepository,
                          StoreRepository storeRepository,
                          MessageService messageService) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.messageService = messageService;
    }

    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) throws NotFoundException {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setAmount(productRequestDTO.getAmount());
        Integer storeId = productRequestDTO.getStoreId();
        Store store = storeRepository.findStoreById(storeId).orElseThrow(
                ()-> new NotFoundException(NotFoundException.ERROR_ROLE_NOT_FOUND,
                        this.messageService.buildMessages
                                ("error.msg.not-found", "info.user.action.create-user"))
        );

        product.setStore(store);
        productRepository.save(product);

        ProductDTO saveProduct = new ProductDTO(product);
        return saveProduct;
    }

}
