package nu.swe.vehicleservice.vehicle.repository;

import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
}
