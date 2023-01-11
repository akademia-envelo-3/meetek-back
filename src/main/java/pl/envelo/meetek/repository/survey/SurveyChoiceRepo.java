package pl.envelo.meetek.repository.survey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.survey.SurveyChoice;

@Repository
public interface SurveyChoiceRepo extends CrudRepository<SurveyChoice, Long> {
}
