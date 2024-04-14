package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.common.model.Role;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends DefaultHttpRepository<Role> {
    @Query(value = "SELECT * FROM Role role WHERE role.id = :roleId", nativeQuery = true)
    Optional<Role> findRoleById(@Param("roleId") Integer roleId);
}
