package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    @Query(value = "SELECT * FROM user u WHERE u.gender = :gender", nativeQuery = true)
    Page<UserDTO> findAllUser(@Param("gender") String gender, Pageable pageable);

}
