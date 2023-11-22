package nu.swe.vehicleservice.corefeatures.file.repository;

import nu.swe.vehicleservice.corefeatures.file.entity.FileLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for handling CRUD operations related to {@link FileLinkEntity}.
 */
public interface FileLinkRepository extends JpaRepository<FileLinkEntity, Long> {
}
