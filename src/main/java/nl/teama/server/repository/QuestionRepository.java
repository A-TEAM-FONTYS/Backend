package nl.teama.server.repository;

import nl.teama.server.entity.Question;
import nl.teama.server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> getAllByQuizAndCategory(Quiz quiz, String category);
}
