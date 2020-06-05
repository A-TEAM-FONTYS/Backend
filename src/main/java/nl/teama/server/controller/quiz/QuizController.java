package nl.teama.server.controller.quiz;

import nl.teama.server.controller.enums.DataResponse;
import nl.teama.server.controller.enums.QuizResponse;
import nl.teama.server.entity.Question;
import nl.teama.server.entity.Quiz;
import nl.teama.server.entity.User;
import nl.teama.server.logic.QuizLogic;
import nl.teama.server.models.QuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(value = "/quiz")
public class QuizController {
    private Logger logger = LoggerFactory.getLogger(QuizController.class);

    private final QuizLogic quizLogic;

    @Autowired
    public QuizController(QuizLogic quizLogic) {
        this.quizLogic = quizLogic;
    }

    @PostMapping
    public ResponseEntity createQuiz(@AuthenticationPrincipal User user) {
        Quiz quiz = this.quizLogic.createQuiz(user);

        if(quiz == null) {
            logger.error("ERROR: create quiz");
            return new ResponseEntity<>(QuizResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(quiz);
    }

    @PostMapping(value = "/{id}/questions")
    public ResponseEntity createQuestions(@PathVariable String id, @Valid @RequestBody QuestionDTO dto) {
        Quiz quiz = this.quizLogic.createQuestions(UUID.fromString(id), dto);

        if(quiz == null) {
            logger.error("ERROR: create questions");
            return new ResponseEntity<>(QuizResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(quiz);
    }

    @GetMapping(value = "/{id}/category/{category}")
    public ResponseEntity getQuestionsByCategory(@PathVariable String id, @PathVariable String category) {
        List<Question> questions = this.quizLogic.getAllByQuizAndCategory(UUID.fromString(id), category);

        if(questions.isEmpty()) {
            logger.error("ERROR: get questions by category");
            return new ResponseEntity<>(QuizResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(questions);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getQuiz(@PathVariable String id) {
        Optional<Quiz> quiz = this.quizLogic.getAllByQuizId(UUID.fromString(id));

        if(!quiz.isPresent()) {
            logger.error("ERROR: get quiz");
            return new ResponseEntity<>(QuizResponse.UNEXPECTED_ERROR.toString(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(quiz.get());
    }
}
