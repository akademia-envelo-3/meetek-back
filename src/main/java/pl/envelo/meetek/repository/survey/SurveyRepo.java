package pl.envelo.meetek.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.model.survey.Survey;

@Repository
public interface SurveyRepo extends JpaRepository<Survey, Long> {
}
