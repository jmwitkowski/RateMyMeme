package pl.sda.ratemymeme.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Meme {
    @Id
    @GeneratedValue(generator = "memeSeq")
    @SequenceGenerator(name = "memeSeq", sequenceName = "meme_seq", allocationSize = 1)
    private int id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    private String nameMeme;
    private LocalDateTime dateUpload;
    private String sourceAdress;
    private int receivedPluses;
    private int receivedMinuses;

    public Meme(String nameMeme, LocalDateTime dateUpload, String sourceAdress) {
        this.nameMeme = nameMeme;
        this.dateUpload = dateUpload;
        this.sourceAdress = sourceAdress;
    }

    public Meme() {
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

    public String getNameMeme() {
        return nameMeme;
    }

    public void setNameMeme(String nameMeme) {
        this.nameMeme = nameMeme;
    }

    public LocalDateTime getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDateTime dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getSourceAdress() {
        return sourceAdress;
    }

    public void setSourceAdress(String sourceAdress) {
        this.sourceAdress = sourceAdress;
    }

    public int getReceivedPluses() {
        return receivedPluses;
    }

    public void setReceivedPluses(int receivedPluses) {
        this.receivedPluses = receivedPluses;
    }

    public int getReceivedMinuses() {
        return receivedMinuses;
    }

    public void setReceivedMinuses(int receivedMinuses) {
        this.receivedMinuses = receivedMinuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meme meme = (Meme) o;
        return id == meme.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
