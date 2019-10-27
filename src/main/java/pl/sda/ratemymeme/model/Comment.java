package pl.sda.ratemymeme.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = "commentSeq")
    @SequenceGenerator(name = "commentSeq", sequenceName = "comment_seq", allocationSize = 1)
    private int idComment;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Meme.class)
    private Meme meme;

    private String content;
    private LocalDate uploadDate;
}
