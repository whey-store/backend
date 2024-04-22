package begin.flywayspringmaven.common.base;

import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.repository.ProductRepository;
import begin.flywayspringmaven.common.repository.StoreRepository;
import begin.flywayspringmaven.common.vo.JwtToken;
import begin.flywayspringmaven.config.jwt.AccessToken;
import begin.flywayspringmaven.config.jwt.JwtProvider;
import begin.flywayspringmaven.util.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseService {

    @Value("${app.paging.default.pageInit}")
    protected int pagingDefaultPageInit;

    @Value("${app.paging.default.limit}")
    protected int pagingDefaultLimit;

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;


    /**
     * Build {@link PageRequest} with page and limit
     *
     * @param page
     * @param limit
     * @param sort
     * @return
     */
    public PageRequest buildPageRequest(Integer page, Integer limit, Sort sort) {
        page = page == null ? 0 : page - this.pagingDefaultPageInit;
        limit = limit == null ? this.pagingDefaultLimit : limit;
        return PageRequest.of(page, limit, sort);
    }

    /**
     * Generate jwt for api
     *
     * @param authentication     Authentication
     * @param isRememberMe remember me
     * @return JwtToken
     */
    public JwtToken jwtForAPIResponse(Authentication authentication, boolean isRememberMe, boolean isCheckLogin) {
        AccessToken accessToken = this.tokenProvider.createAccessToken(authentication, isRememberMe, isCheckLogin);
        return new JwtToken(accessToken.getToken(), Constants.JWT_TOKEN_TYPE, accessToken.getRefreshToken());
    }

    /**
     * Get Store by Id
     * @Param storeId
     * return Store
     */
    public Store getStoreById(Integer storeId) {
        return this.storeRepository.findStoreById(storeId).orElse(null);
    }

    /**
     * Get Product by id
     * @Param productId
     * return Product
     */
    public Product getProductById(Integer productId) {
        return this.productRepository.findProductById(productId).orElse(null);
    }
}
