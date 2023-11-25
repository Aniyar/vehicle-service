package nu.swe.vehicleservice.maintenance.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.maintenance.enums.MaintenanceType;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "maintenances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update maintenances set is_deleted = 1, deleted_at = now() where id = ?")
public class MaintenanceEntity extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity maintenancePersonnel;

    @Column(name="maintenance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaintenanceType maintenanceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @Column(name="details", nullable = false)
    private String details;
}
