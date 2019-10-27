package pl.sda.ratemymeme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Entity
public class Meme {
    @Id
    @GeneratedValue(generator = "memeSeq")
    @SequenceGenerator(name = "memeSeq", sequenceName = "meme_seq", allocationSize = 1)
    private int idMeme;
    
    private User user;
    private String nameMeme;
    private LocalDate dateUpload;
    private String sourceAdress;
    private int receivedPluses;
    private int receivedMinuses;
}
