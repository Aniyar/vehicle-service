package nu.swe.vehicleservice.user.entity;

import jakarta.persistence.*;
import lombok.*;
import nu.swe.vehicleservice.core.entity.AuditModel;
import nu.swe.vehicleservice.user.enums.UserRole;
import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "vehicle_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update vehicle_users set is_deleted = 1, deleted_at = now() where id = ?")
public class UserEntity extends AuditModel {

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "file_link_id")
    @Comment("Represents the file link associated with the applicant's profile picture")
    private FileLinkEntity fileLink;
}
