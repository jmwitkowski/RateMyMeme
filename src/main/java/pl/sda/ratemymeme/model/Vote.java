package pl.sda.ratemymeme.model;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(generator = "voteSeq")
    @SequenceGenerator(name = "voleSeq", sequenceName = "vote_seq", allocationSize = 1)
    private int idVote;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Meme.class)
    private Meme meme;

    private boolean voted;

}
