package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Voucher;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends DefaultHttpRepository<Voucher> {
}
