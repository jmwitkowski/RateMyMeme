package pl.sda.ratemymeme.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.model.Vote;
import pl.sda.ratemymeme.repository.MemeRepository;
import pl.sda.ratemymeme.repository.VoteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoteService {
   private final VoteRepository voteRepository;
   private final UserService userService;
    private final MemeRepository memeRepository;

    public VoteService(VoteRepository voteRepository, UserService userService,  MemeRepository memeRepository) {
        this.voteRepository = voteRepository;
        this.userService = userService;
        this.memeRepository = memeRepository;
    }

    public void votePlus(Meme meme) {
        User userFrom = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Vote vote = new Vote(userFrom, meme);
        voteRepository.save(vote);
        int actualResult = meme.getReceivedPluses();
        meme.setReceivedPluses(actualResult+1);
        memeRepository.save(meme);
    }
    public List<String> getListOfUsersWhoVotedOnThisMeme(Meme meme){
        List<String> listOfUserNamesWhoVoted = voteRepository.findByMeme_Id(meme.getId()).stream()
                .map(vote -> vote.getUser().getLogin())
                .collect(Collectors.toList());
        return listOfUserNamesWhoVoted;
    }
}
