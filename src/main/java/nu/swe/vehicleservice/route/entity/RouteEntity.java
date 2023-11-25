package nu.swe.vehicleservice.route.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.driver.entity.DriverEntity;
import nu.swe.vehicleservice.route.enums.RouteStatus;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update routes set is_deleted = 1, deleted_at = now() where id = ?")
public class RouteEntity extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private UserEntity staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @Column(name="start_point", nullable = false)
    private String startPoint;

    @Column(name="end_point", nullable = false)
    private String endPoint;

    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column(name="end_time")
    private LocalDateTime endTime;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteStatus status;
}
