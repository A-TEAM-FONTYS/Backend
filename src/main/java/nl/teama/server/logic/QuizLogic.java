package nl.teama.server.logic;

import nl.teama.server.entity.Question;
import nl.teama.server.entity.Quiz;
import nl.teama.server.entity.User;
import nl.teama.server.models.QuestionDTO;
import nl.teama.server.service.QuestionService;
import nl.teama.server.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuizLogic {
    private final QuizService quizService;
    private final QuestionService questionService;

    @Autowired
    public QuizLogic(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    public Quiz createQuiz(User user) {
        return this.quizService.createOrUpdate(new Quiz(user));
    }

    public Quiz createQuestions(UUID quizId, QuestionDTO dto) {
        Optional<Quiz> quiz = this.quizService.findById(quizId);

        if(quiz.isPresent()) {
            for(Question question : dto.getQuestions()) {

                question.addQuiz(quiz.get());
                Question created = this.questionService.createOrUpdate(question);

                if(created == null) {
                    return null;
                }
            }

            return quiz.get();
        }

        return null;
    }

    public List<Question> getAllByQuizAndCategory(UUID quizId, String category) {
        Optional<Quiz> quiz = this.quizService.findById(quizId);

        if(quiz.isPresent()) {
            return this.questionService.getAllByQuizAndCategory(quiz.get(), category);
        }

        return Collections.emptyList();
    }

    public Optional<Quiz> getAllByQuizId(UUID id) {
        return this.quizService.findById(id);
    }
}
