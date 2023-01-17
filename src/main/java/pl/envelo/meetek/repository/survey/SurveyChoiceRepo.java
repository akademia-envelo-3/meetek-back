package pl.envelo.meetek.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.survey.SurveyChoice;

@Repository
public interface SurveyChoiceRepo extends JpaRepository<SurveyChoice, Long> {
}
