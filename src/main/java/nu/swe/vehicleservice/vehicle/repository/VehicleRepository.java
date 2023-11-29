package nu.swe.vehicleservice.vehicle.repository;

import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByLicencePlate(String vehiclePlate);
}
