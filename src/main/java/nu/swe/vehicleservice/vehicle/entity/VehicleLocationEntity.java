package nu.swe.vehicleservice.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "vehicle_locations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update vehicle_locations set is_deleted = 1, deleted_at = now() where id = ?")
public class VehicleLocationEntity extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false)
    private String latitude;
}
