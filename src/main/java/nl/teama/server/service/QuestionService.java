package nl.teama.server.service;

import nl.teama.server.entity.Question;
import nl.teama.server.entity.Quiz;
import nl.teama.server.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createOrUpdate(Question question) {
        return this.questionRepository.save(question);
    }

    public List<Question> getAllByQuizAndCategory(Quiz quiz, String category) {
        return this.questionRepository.getAllByQuizAndCategory(quiz, category);
    }
}
