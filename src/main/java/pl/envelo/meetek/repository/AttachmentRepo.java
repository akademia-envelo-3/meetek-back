package pl.envelo.meetek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Attachment;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
}
