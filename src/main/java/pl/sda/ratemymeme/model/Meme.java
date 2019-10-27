package pl.sda.ratemymeme.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Meme {
    @Id
    @GeneratedValue(generator = "memeSeq")
    @SequenceGenerator(name = "memeSeq", sequenceName = "meme_seq", allocationSize = 1)
    private int idMeme;

    @OneToOne(targetEntity = User.class)
    private User user;

    private String nameMeme;
    private LocalDate dateUpload;
    private String sourceAdress;
    private int receivedPluses;
    private int receivedMinuses;
}
