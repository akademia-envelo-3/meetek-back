package pl.envelo.meetek.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.survey.model.SurveyResponse;

@Repository
public interface SurveyResponseRepo extends JpaRepository<SurveyResponse, Long> {
}
