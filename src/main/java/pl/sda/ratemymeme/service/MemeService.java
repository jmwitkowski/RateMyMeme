package pl.sda.ratemymeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;

import java.io.File;
import java.time.LocalDate;

@Service
public class MemeService {
    private final MemeRepository memeRepository;

    @Autowired
    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public void addMemeToDataBase(User user, String memeName, String sourceAdress ) {
        Meme memeToAdd = new Meme(user,memeName, LocalDate.now(),sourceAdress);
        memeRepository.save(memeToAdd);
    }
}
