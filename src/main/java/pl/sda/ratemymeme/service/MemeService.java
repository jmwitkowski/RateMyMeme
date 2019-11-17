package pl.sda.ratemymeme.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.exception.IndexOutOfListOfeMemesException;
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
    private int memesOnPage;


    @Autowired
    public MemeService(MemeRepository memeRepository, UserService userService, VoteService voteService, @Value("${memesonpage}") int memesOnPage) {
        this.memeRepository = memeRepository;
        this.userService = userService;
        this.voteService = voteService;
        this.memesOnPage = memesOnPage;
    }

    public Meme addMemeToDataBase(String memeName, String sourceAdress) {
        User userFrom = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Meme memeToAdd = new Meme(memeName, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), sourceAdress);
        memeToAdd.setUser(userFrom);
        return memeRepository.save(memeToAdd);
    }

    public List<MemeWithVotes> getAllMemes() {
        List<Meme> allMemesList = memeRepository.findAll();
        Collections.sort(allMemesList, (Meme m1, Meme m2) -> m2.getDateUpload().compareTo(m1.getDateUpload()));
        return allMemesList.stream()
                .map(memeFromList -> new MemeWithVotes(memeFromList, voteService.getListOfUsersWhoVotedOnThisMeme(memeFromList)))
                .collect(Collectors.toList());
    }

    public Meme getMemeById(int id) throws MemeNotFoundException {
        Optional<Meme> optMeme = memeRepository.findById(id);
        if (!optMeme.isPresent()) {
            throw new MemeNotFoundException("Could not find meme with id: " + id);
        }
        return optMeme.get();
    }

    public MemeWithVotes getMemeWithVote(Meme meme) {
        return new MemeWithVotes(meme, voteService.getListOfUsersWhoVotedOnThisMeme(meme));
    }

    public void deleteMeme(Meme meme) {
        memeRepository.deleteById(meme.getId());
    }

    public List<MemeWithVotes> getAllMemesPaginated(int pageIndex) {
        int lastMemeOnPage = pageIndex * memesOnPage;
        int sizeOfMemeList = getAllMemes().size();

        if ((lastMemeOnPage - memesOnPage) >= sizeOfMemeList) {
            throw new IndexOutOfListOfeMemesException("Index of page out of list of memes");
        } else if (lastMemeOnPage > sizeOfMemeList && (lastMemeOnPage - memesOnPage) < sizeOfMemeList) {
            return getAllMemes().stream().skip((pageIndex - 1) * memesOnPage).collect(Collectors.toList());
        }
        return getAllMemes().subList(lastMemeOnPage - memesOnPage, lastMemeOnPage);
    }

    public boolean isLastPage(int pageIndex) {
        int lastMemeOnPage = pageIndex * memesOnPage;
        int sizeOfMemeList = getAllMemes().size();
        return lastMemeOnPage > sizeOfMemeList && (lastMemeOnPage - memesOnPage) < sizeOfMemeList;
    }
}
