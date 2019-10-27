package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = "commentSeq")
    @SequenceGenerator(name = "commentSeq", sequenceName = "comment_seq", allocationSize = 1)
    private int idComment;

    private User user;

    private Meme meme;
    private String content;
    private LocalDate uploadDate;
}
