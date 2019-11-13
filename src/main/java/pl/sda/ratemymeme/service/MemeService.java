package pl.sda.ratemymeme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.exception.MemeNotFoundException;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.MemeWithVotes;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MemeService {
    private final MemeRepository memeRepository;
    private final UserService userService;
    private final VoteService voteService;


    @Autowired
    public MemeService(MemeRepository memeRepository, UserService userService, VoteService voteService) {
        this.memeRepository = memeRepository;
        this.userService = userService;
        this.voteService = voteService;
    }

    public void addMemeToDataBase(String memeName, String sourceAdress) {
        User userFrom = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Meme memeToAdd = new Meme(memeName, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), sourceAdress);
        memeToAdd.setUser(userFrom);
        memeRepository.save(memeToAdd);
    }

    public List<MemeWithVotes> getAllMemes() {
        List<MemeWithVotes> memeWithVotesList;
        List<Meme> allMemesList = memeRepository.findAll();
        Collections.sort(allMemesList, (Meme m1, Meme m2) -> m2.getDateUpload().compareTo(m1.getDateUpload()));
        memeWithVotesList = allMemesList.stream()
                .map(memeFromList -> new MemeWithVotes(memeFromList, voteService.getListOfUsersWhoVotedOnThisMeme(memeFromList)))
                .collect(Collectors.toList());
        return memeWithVotesList;
    }

    public Meme getMemeById(int id) throws MemeNotFoundException{
        Optional<Meme> optMeme = memeRepository.findById(id);
        if(!optMeme.isPresent()){
            throw new MemeNotFoundException("Could not find meme with id: " + id);
        }
        return optMeme.get();
    }

    public MemeWithVotes getMemeWithVote(Meme meme) {
        return new MemeWithVotes(meme,voteService.getListOfUsersWhoVotedOnThisMeme(meme));
    }

    public void deleteMeme(Meme meme) {
        memeRepository.deleteById(meme.getId());
    }
}
