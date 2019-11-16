package pl.sda.ratemymeme.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.model.Vote;
import pl.sda.ratemymeme.repository.MemeRepository;
import pl.sda.ratemymeme.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.Mockito.*;
public class VoteServiceTest {
    private final Role roleUser = new Role("USER");
    private final Meme meme1 = new Meme("Meme1", LocalDateTime.now(), "memebase/kiedys-to-byli-programisci.jpg");
    private final User user1 = new User("User1", "user@gmail.com", "password", LocalDate.now(), roleUser);
    private final Vote vote1 = new Vote(user1,meme1);
    private final VoteRepository voteRepositoryMock = mock(VoteRepository.class);
    private final UserService userServiceMock = mock(UserService.class);
    private final MemeRepository memeRepositoryMock = mock(MemeRepository.class);
    private final VoteService voteService = new VoteService(voteRepositoryMock, userServiceMock,memeRepositoryMock);

    @BeforeEach
    public void init() {
        SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication("user1"));
    }

    @Test
    public void shouldGetListOfUsersWhoVotedOnMeme(){
        //Given
        List<Vote> listVotes = new ArrayList<>();
        listVotes.add(vote1);
        when(voteRepositoryMock.findByMeme_Id(meme1.getId())).thenReturn(listVotes);
        //When
        List<String> listOfUserNames = voteService.getListOfUsersWhoVotedOnThisMeme(meme1);
        int expectedLength = 1;
        //Then
        assertThat(listOfUserNames.size()).isEqualTo(expectedLength);
    }
    @Test
    public void shouldVotePlus(){
        //When
        voteService.votePlus(meme1);
        //Then
        verify(voteRepositoryMock,times(1)).save(vote1);
        verify(memeRepositoryMock,times(1)).save(meme1);
    }

    @Test
    public void shouldVoteMinus(){
        //When
        voteService.voteMinus(meme1);
        //Then
        verify(voteRepositoryMock,times(1)).save(vote1);
        verify(memeRepositoryMock,times(1)).save(meme1);
    }


}