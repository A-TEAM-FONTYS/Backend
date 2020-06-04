package nl.teama.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Quiz extends BaseEntity implements Serializable {

    @OneToOne
    @NotNull
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="quiz", orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    public Quiz() {}

    public Quiz(User user) {
        this.user = user;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
