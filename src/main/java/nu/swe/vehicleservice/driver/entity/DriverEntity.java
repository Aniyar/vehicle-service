package nu.swe.vehicleservice.driver.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update drivers set is_deleted = 1, deleted_at = now() where id = ?")
public class DriverEntity extends AuditModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "address")
    private String address;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "rating")
    private BigDecimal rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;
}
