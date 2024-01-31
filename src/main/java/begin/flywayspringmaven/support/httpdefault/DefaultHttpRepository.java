package begin.flywayspringmaven.support.httpdefault;

import begin.flywayspringmaven.common.base.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DefaultHttpRepository<T extends BaseModel> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {
}
