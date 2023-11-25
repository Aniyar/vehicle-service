package nu.swe.vehicleservice.maintenance.repository;

import nu.swe.vehicleservice.maintenance.entity.MaintenanceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long>, JpaSpecificationExecutor<MaintenanceEntity> {

    Page<MaintenanceEntity> findAllByVehicleId(Long vehicleId, Pageable pageable);
}
