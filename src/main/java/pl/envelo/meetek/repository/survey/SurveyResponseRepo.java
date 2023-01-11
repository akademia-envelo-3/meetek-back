package pl.envelo.meetek.repository.survey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.survey.SurveyResponse;

@Repository
public interface SurveyResponseRepo extends CrudRepository<SurveyResponse, Long> {
}
