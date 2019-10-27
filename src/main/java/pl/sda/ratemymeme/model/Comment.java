package pl.sda.ratemymeme.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = "commentSeq")
    @SequenceGenerator(name = "commentSeq", sequenceName = "comment_seq", allocationSize = 1)
    private int idComment;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Meme.class)
    private Meme meme;

    private String content;
    private LocalDate uploadDate;
}
