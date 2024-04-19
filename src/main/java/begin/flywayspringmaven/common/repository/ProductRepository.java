package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends DefaultHttpRepository<Product> {

    /**
     * Find ROLE_ADMIN of product according to the parameter
     *
     * @param query
     * @param pageable
     * @return Product
     */
    @Query(value = "SELECT * FROM product"
            + " WHERE product.deleted IS NULL "
            + " AND (product.name LIKE %:query% OR product.description LIKE %:query%)"
            + "ORDER BY product.modified ASC", nativeQuery = true
    )
    Page<Product> getProductListByAdmin(@Param("query") String query, Pageable pageable);
}
