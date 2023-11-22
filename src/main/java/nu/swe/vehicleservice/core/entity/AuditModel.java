package nu.swe.vehicleservice.core.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * This is the base class for JPA entities.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private Byte isDeleted = 0;

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
