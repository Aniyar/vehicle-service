package nu.swe.vehicleservice.driver.repository;

import nu.swe.vehicleservice.driver.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    Optional<DriverEntity> findByUserId(Long userId);

    Optional<DriverEntity> findByVehicleId(Long vehicleId);
}
