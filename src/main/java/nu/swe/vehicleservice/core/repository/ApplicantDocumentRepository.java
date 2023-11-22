package nu.swe.vehicleservice.core.repository;

import nu.swe.vehicleservice.core.entity.ApplicantDocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link ApplicantDocumentEntity} entity.
 */
public interface ApplicantDocumentRepository extends JpaRepository<ApplicantDocumentEntity, Long> {

    /**
     * Retrieves a paginated list of {@link ApplicantDocumentEntity} entities associated with a
     * given applicant's UUID.
     *
     * @param uuid     The UUID of the applicant to retrieve documents for.
     * @param pageable The pagination and sorting information.
     * @return A paginated list of applicant documents.
     */
    Page<ApplicantDocumentEntity> findAllByApplicantId(String uuid, Pageable pageable);

    /**
     * Counts the number of documents associated with a given applicant's UUID.
     *
     * @param uuid The UUID of the applicant for which to count documents.
     * @return The count of documents associated with the given applicant UUID.
     */
    long countByApplicantId(String uuid);

    /**
     * Determine if user has any document.
     *
     * @param applicantId The UUID if the applicant to determine existence of a document.
     * @return true if any, false if none
     */
    boolean existsByApplicantId(String applicantId);

}
