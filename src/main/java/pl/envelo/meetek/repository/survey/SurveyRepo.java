package pl.envelo.meetek.repository.survey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.survey.Survey;

@Repository
public interface SurveyRepo extends CrudRepository<Survey, Long> {
}
