package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends DefaultHttpRepository<User> {
    @Query(value = "SELECT * FROM user", nativeQuery = true)
    Page<UserDTO> findAllUser(Pageable pageable);

    @Query(value = "SELECT * FROM user u WHERE u.id = :userId", nativeQuery = true)
    Optional<User> findUserById(@Param("userId") Integer userId);
}
