package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Voucher;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends DefaultHttpRepository<Voucher> {

    @Query(value = "SELECT * FROM voucher WHERE voucher.id IN :listVoucherIds", nativeQuery = true)
    List<Voucher> findVoucherByListId(@Param("listVoucherIds") List<Integer> listVoucherIds);


}
