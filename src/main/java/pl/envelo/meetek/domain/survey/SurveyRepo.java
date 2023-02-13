package pl.envelo.meetek.domain.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.envelo.meetek.domain.survey.model.Survey;

import java.util.Optional;

@Repository
public interface SurveyRepo extends JpaRepository<Survey, Long> {

    Optional<Survey> findByQuestion(String question);

}
