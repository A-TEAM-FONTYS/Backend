package nl.teama.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter @Setter
public class Question extends BaseEntity implements Serializable {

    @NotNull
    private String ask;

    @NotNull
    private String answer;

    @NotNull
    private String category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="quiz_id", nullable=false)
    private Quiz quiz;

    public Question() {}

    public void addQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
