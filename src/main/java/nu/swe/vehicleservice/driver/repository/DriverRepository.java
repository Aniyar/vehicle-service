package nu.swe.vehicleservice.driver.repository;

import nu.swe.vehicleservice.driver.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {
    Optional<DriverEntity> findByUserId(Integer userId);

    Optional<DriverEntity> findByVehicleId(Integer vehicleId);
}
