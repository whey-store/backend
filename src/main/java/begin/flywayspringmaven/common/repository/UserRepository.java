package begin.flywayspringmaven.common.repository;

import begin.flywayspringmaven.api.user.dto.UserDTO;
import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.support.httpdefault.DefaultHttpRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends DefaultHttpRepository<User> {

    /**
     * Find ROLE_ADMIN of user according to the parameter
     *
     * @param query
     * @param pageable
     * @return {@link UserDTO}
     */
    @Query(value = "SELECT * FROM user"
            + " WHERE user.deleted IS NULL "
            + " AND (user.username LIKE %:query% OR user.email LIKE %:query%)"
            + " ORDER BY user.modified ASC", nativeQuery = true
    )
    Page<User> findAllUserByConditions(@Param("query") String query, Pageable pageable);

    /**
     * Find user by id
     *
     * @param userId
     * @return
     */
    @Query(value = "SELECT * FROM user u WHERE u.id = :userId", nativeQuery = true)
    Optional<User> findUserById(@Param("userId") Integer userId);

    /**
     * Find User By id
     *
     * @param email
     * @return
     */
    @Query(value = "SELECT * FROM user u WHERE u.email = :email LIMIT 1 ", nativeQuery = true)
    Optional<User> findUserByEmailLogin(String email);

    /**
     * Find User By id
     *
     * @param email
     * @return
     */
    @Query(value = "SELECT * FROM user u WHERE u.email = :email LIMIT 1 ", nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
