package nu.swe.vehicleservice.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.vehicle.enums.VehicleFuelType;
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

    @Column(name="model")
    private String model;

    @Column(name="year")
    private Integer year;

    @Column(name="licence_plate")
    private String licencePlate;

    @Column(name="capacity")
    private Integer capacity;

    @Column(name="vin")
    private String vin;

    @Column(name="fuel_type")
    @Enumerated(EnumType.STRING)
    private VehicleFuelType fuelType;
}
