package pl.sda.ratemymeme.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vote {

    @Id
    @GeneratedValue(generator = "voteSeq")
    @SequenceGenerator(name = "voteSeq", sequenceName = "vote_seq", allocationSize = 1)
    private int id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Meme.class)
    private Meme meme;


    public Vote(User user, Meme meme) {
        this.user = user;
        this.meme = meme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meme getMeme() {
        return meme;
    }

    public void setMeme(Meme meme) {
        this.meme = meme;
    }

    public Vote() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
