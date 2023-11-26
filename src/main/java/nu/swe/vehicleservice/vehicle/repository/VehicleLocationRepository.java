package nu.swe.vehicleservice.vehicle.repository;

import nu.swe.vehicleservice.vehicle.entity.VehicleLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleLocationRepository extends JpaRepository<VehicleLocationEntity, Long> {
    Optional<VehicleLocationEntity> findTop1ByVehicleIdOrderByCreatedAtDesc(Long id);

}
