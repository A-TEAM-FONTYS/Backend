package nl.teama.server.models;

import lombok.Data;
import nl.teama.server.entity.Question;

import java.util.List;

@Data
public class QuestionDTO {
    private List<Question> questions;
}
