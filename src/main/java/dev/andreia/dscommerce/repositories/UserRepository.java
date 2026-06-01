package dev.andreia.dscommerce.repositories;

import dev.andreia.dscommerce.entities.User;
import dev.andreia.dscommerce.projections.UserRoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value =
            "SELECT u.email AS username, u.password, r.id, r.authority " +
            "FROM tb_user u " +
            "INNER JOIN tb_user_role ur " +
            "ON u.id = ur.user_id " +
            "INNER JOIN tb_role r " +
            "ON ur.role_id = r.id " +
            "WHERE u.email = :email")
    List<UserRoleProjection> searchUserAndRolesByEmail(String email);

    Optional<User> findByEmail(String email);
}
