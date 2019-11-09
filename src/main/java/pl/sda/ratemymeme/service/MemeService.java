package pl.sda.ratemymeme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.repository.MemeRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class MemeService {
    private final MemeRepository memeRepository;


    @Autowired
    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public void addMemeToDataBase(String memeName, String sourceAdress) {
        Meme memeToAdd = new Meme(memeName, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), sourceAdress);
        memeRepository.save(memeToAdd);
    }

    public List<Meme> getAllMemes() {
        List<Meme> allMemesList = memeRepository.findAll();

        Collections.sort(allMemesList, (Meme m1, Meme m2) -> m2.getDateUpload().compareTo(m1.getDateUpload()));

        return allMemesList;
    }
}
