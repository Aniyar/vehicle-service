package nu.swe.vehicleservice.core.entity;

import jakarta.persistence.*;
import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Represents an applicant's document. This entity is used to
 * associate documents (or file links) to a particular applicant.
 */
@Entity
@Table(name = "applicant_documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update applicant_documents set is_deleted = 1, deleted_at = now() where id = ?")
public class ApplicantDocumentEntity extends AuditModel {

    @Column(name = "applicant_uuid", nullable = false)
    @Comment("The UUID associated with an applicant")
    private String applicantId;

    @ManyToOne
    @JoinColumn(name = "file_link_id", nullable = false)
    @Comment("Represents the file link associated with the applicant's document")
    private FileLinkEntity fileLink;

}
