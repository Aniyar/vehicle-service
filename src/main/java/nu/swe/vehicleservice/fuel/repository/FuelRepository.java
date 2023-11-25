package nu.swe.vehicleservice.fuel.repository;

import nu.swe.vehicleservice.fuel.entity.FuelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FuelRepository extends JpaRepository<FuelEntity, Long>, JpaSpecificationExecutor<FuelEntity> {

    Page<FuelEntity> findAllByVehicleId(Long vehicleId, Pageable pageable);
}
