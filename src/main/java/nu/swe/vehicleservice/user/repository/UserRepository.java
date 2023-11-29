package nu.swe.vehicleservice.user.repository;

import nu.swe.vehicleservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * Find user by username.
     *
     * @param username String username.
     * @return optional user entity.
     */
    Optional<UserEntity> findByUsername(String username);
}
