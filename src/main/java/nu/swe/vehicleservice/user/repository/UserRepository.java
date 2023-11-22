package nu.swe.vehicleservice.user.repository;

import nu.swe.vehicleservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Find user by username.
     *
     * @param username String username.
     * @return optional user entity.
     */
    Optional<UserEntity> findByUsername(String username);
}
