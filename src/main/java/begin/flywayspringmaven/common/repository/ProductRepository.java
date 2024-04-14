package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Product;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends DefaultHttpRepository<Product> {
}
