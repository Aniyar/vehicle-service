package nu.swe.vehicleservice.auction.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.user.entity.UserEntity;
import nu.swe.vehicleservice.vehicle.entity.VehicleEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "auctions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update auctions set is_deleted = 1, deleted_at = now() where id = ?")
public class AuctionEntity extends AuditModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Column(name="current_bid", nullable = false)
    private BigDecimal currentBid;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid_user_id")
    private UserEntity currentBidHolder;
}
