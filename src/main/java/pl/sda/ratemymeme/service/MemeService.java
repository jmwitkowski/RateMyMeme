package pl.sda.ratemymeme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemeService {
    private final MemeRepository memeRepository;

    @Autowired
    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public void addMemeToDataBase(String memeName, String sourceAdress ) {
        Meme memeToAdd = new Meme(memeName, LocalDate.now(),sourceAdress);
        memeRepository.save(memeToAdd);
    }

    public List<Meme> getAllMemes(){
       List<Meme> allMemesList =  memeRepository.findAll();
       return allMemesList;
    }
}
