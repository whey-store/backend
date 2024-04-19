package begin.flywayspringmaven.api.product;

import begin.flywayspringmaven.api.product.dto.ProductDTO;
import begin.flywayspringmaven.api.product.dto.ProductRequestDTO;
import begin.flywayspringmaven.common.base.BaseService;
import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.repository.ProductRepository;
import begin.flywayspringmaven.common.service.MessageService;
import begin.flywayspringmaven.common.vo.PageInfo;
import begin.flywayspringmaven.exception.NotFoundException;
import begin.flywayspringmaven.util.FlywaySpringUtils;
import begin.flywayspringmaven.util.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService extends BaseService {
    private final ProductRepository productRepository;
    private final MessageService messageService;

    public ProductService(ProductRepository productRepository,
                          MessageService messageService ) {
        this.productRepository = productRepository;
        this.messageService = messageService;
    }

    /**
     * Get list product by admin
     * @param query
     * @param page
     * @param limit
     * @param strSort
     * @return
     */
    public PageInfo<ProductDTO> getProductAdminList(String query, Integer page, Integer limit, String strSort) throws Exception {
        Sort sort = Sort.by(Sort.Order.asc("product.id"));
        switch (strSort) {
            case Constants.SORT_OLDEST_TO_NEWEST:
                sort = Sort.by(Sort.Order.asc("product.modified"));
                break;

            case Constants.SORT_NEWEST_TO_OLDEST:
                sort = Sort.by(Sort.Order.desc("product.modified"));
                break;
            default:
                break;
        }
        String resultQuery = StringUtils.isEmpty(query) ? "" : query;
        PageRequest pageRequest = this.buildPageRequest(page, limit, sort);
        Page<ProductDTO> data = productRepository.getProductListByAdmin(resultQuery, pageRequest).map(productDTO -> new ProductDTO(productDTO));
        return FlywaySpringUtils.pagingResponse(data);
    }

    /**
     * Create product
     *
     * @param productRequestDTO
     * @return
     * @throws NotFoundException
     */
    @Transactional(rollbackFor = Exception.class)
    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) throws NotFoundException {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setAmount(productRequestDTO.getAmount());
        Integer storeId = productRequestDTO.getStoreId();
        Store store = this.getStoreById(storeId);
        if (store == null) {
            throw new NotFoundException(NotFoundException.ERROR_STORE_NOT_FOUND,
                this.messageService.buildMessages("error.msg.not-found", "info.store"));
        }
        product.setStore(store);
        productRepository.save(product);

        ProductDTO saveProduct = new ProductDTO(product);
        return saveProduct;
    }

}
