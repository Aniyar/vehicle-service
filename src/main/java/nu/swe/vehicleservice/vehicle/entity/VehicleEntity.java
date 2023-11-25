package nu.swe.vehicleservice.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.fuel.enums.FuelType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update vehicles set is_deleted = 1, deleted_at = now() where id = ?")
public class VehicleEntity extends AuditModel {

    @Column(name="model", nullable = false)
    private String model;

    @Column(name="make_year", nullable = false)
    private Integer year;

    @Column(name="licence_plate", nullable = false)
    private String licencePlate;

    @Column(name="capacity", nullable = false)
    private Integer capacity;

    @Column(name="vin", nullable = false)
    private String vin;

    @Column(name="fuel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
}
