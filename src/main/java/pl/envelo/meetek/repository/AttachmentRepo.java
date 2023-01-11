package pl.envelo.meetek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.Attachment;

@Repository
public interface AttachmentRepo extends CrudRepository<Attachment, Long> {
}
