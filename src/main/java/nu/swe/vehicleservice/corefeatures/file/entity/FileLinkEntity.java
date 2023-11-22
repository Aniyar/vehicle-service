package nu.swe.vehicleservice.corefeatures.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nu.swe.vehicleservice.core.entity.AuditModel;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Represents a file link entity in the system.
 * <p>This entity serves as a reference to files stored in MinIO.
 * Each file link is associated with an owner, has a unique storage key, is stored in a specified bucket.</p>
 */
@Entity
@Table(name = "file_links")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update file_links set is_deleted = 1, deleted_at = now() where id = ?")
public class FileLinkEntity extends AuditModel {

    @Column(name = "bucket_name", nullable = false)
    @Comment("Bucket name")
    private String bucketName;

    @Column(name = "storage_key", nullable = false)
    @Comment("Unique key for the file in file storage")
    private String storageKey;

    @Column(name = "file_name", nullable = false)
    @Comment("Original name of the file")
    private String fileName;

    @Column(name = "content_type")
    @Comment("MIME type of the content")
    private String contentType;

    @Column(name = "is_viewable")
    @Comment("Flag indicating whether file should be viewed in browser/app or be downloaded as an attachment")
    private Boolean isViewable;
}
