package pl.sda.ratemymeme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.exception.MemeNotFoundException;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class MemeService {
    private final MemeRepository memeRepository;
    private final UserService userService;


    @Autowired
    public MemeService(MemeRepository memeRepository, UserService userService) {
        this.memeRepository = memeRepository;
        this.userService = userService;
    }

    public void addMemeToDataBase(String memeName, String sourceAdress) {
        User userFrom = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Meme memeToAdd = new Meme(memeName, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), sourceAdress);
        memeToAdd.setUser(userFrom);
        memeRepository.save(memeToAdd);
    }

    public List<Meme> getAllMemes() {
        List<Meme> allMemesList = memeRepository.findAll();

        Collections.sort(allMemesList, (Meme m1, Meme m2) -> m2.getDateUpload().compareTo(m1.getDateUpload()));

        return allMemesList;
    }

    public Meme getMemeById(int id) throws MemeNotFoundException{
        Optional<Meme> optMeme = memeRepository.findById(id);
        if(!optMeme.isPresent()){
            throw new MemeNotFoundException("Could not find meme with id: " + id);
        }
        return optMeme.get();
    }
}
