package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Store;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends DefaultHttpRepository<Store> {
    @Query(value = "SELECT * FROM store WHERE store.id = :storeId", nativeQuery = true)
    Optional<Store> findStoreById(@Param("storeId") Integer storeId);
}
