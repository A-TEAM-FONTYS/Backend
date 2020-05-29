package nl.teama.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Quiz extends BaseEntity implements Serializable {
}
