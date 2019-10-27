package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Vote {

    @Id
    @GeneratedValue(generator = "voteSeq")
    @SequenceGenerator(name = "voleSeq", sequenceName = "vote_seq", allocationSize = 1)
    private int idVote;

    private User userLogin;
    private Meme meme;
    private boolean voted;

}
