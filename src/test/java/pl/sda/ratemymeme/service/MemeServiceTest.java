package pl.sda.ratemymeme.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.MemeWithVotes;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class MemeServiceTest {
    private Role roleUser = new Role("USER");
    private User user1 = new User("User1", "user@gmail.com", "password", LocalDate.now(), roleUser);
    private Meme meme1 = new Meme("Meme1", LocalDateTime.now(), "memebase/kiedys-to-byli-programisci.jpg");
    private Meme meme2 = new Meme("Meme2", LocalDateTime.now(), "memebase/HehehelloMEME.jpg");
    private Meme meme3 = new Meme("Meme3", LocalDateTime.now(), "memebase/66QC917gOTDokD7R.jpg");


    private UserService userServiceMock = mock(UserService.class);
    private MemeRepository memeRepositoryMock = mock(MemeRepository.class);
    private VoteService voteServiceMock = mock(VoteService.class);


    private MemeService memeService = new MemeService(memeRepositoryMock, userServiceMock, voteServiceMock);

    @BeforeEach
    public void init() {
        SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication("user1"));
    }

    @Test
    public void shouldAddMemeToDataBase() {
        //given
        when(userServiceMock.findByLogin("user1")).thenReturn(user1);
        when(memeRepositoryMock.save(meme3)).thenReturn(meme3);
        //When
        Meme meme = memeService.addMemeToDataBase(meme3.getNameMeme(), meme3.getSourceAdress());
        //then
        assertThat(meme).isEqualTo(meme3);
    }


    @Test
    public void shouldGetMemeById() {
        //given
        when(memeRepositoryMock.findById(3)).thenReturn(Optional.of(meme3));
        //When
        Meme meme = memeService.getMemeById(3);
        //then
        assertThat(meme).isEqualTo(meme3);
    }

    @Test
    public void shouldGetAllMemes() {
        //Given
        List<Meme> listOfMeme = new ArrayList<>();
        listOfMeme.add(meme1);
        listOfMeme.add(meme2);
        when(memeRepositoryMock.findAll()).thenReturn(listOfMeme);

        List<String> meme1UserWhiVotes = new ArrayList<>();
        meme1UserWhiVotes.add(user1.getUsername());
        when(voteServiceMock.getListOfUsersWhoVotedOnThisMeme(meme1)).thenReturn(meme1UserWhiVotes);
        List<String> meme2UserWhiVotes = new ArrayList<>();
        meme1UserWhiVotes.add(user1.getUsername());
        when(voteServiceMock.getListOfUsersWhoVotedOnThisMeme(meme2)).thenReturn(meme2UserWhiVotes);
        List<String> meme3UserWhiVotes = new ArrayList<>();
        meme1UserWhiVotes.add(user1.getUsername());
        when(voteServiceMock.getListOfUsersWhoVotedOnThisMeme(meme3)).thenReturn(meme3UserWhiVotes);

        //When
        List<MemeWithVotes> listOfMemesWithVote = memeService.getAllMemes();
        int listLength = listOfMemesWithVote.size();
        int expected = 2;
        //then
        assertThat(listLength).isEqualTo(expected);
    }

    @Test
    public void shouldDeleteMemeById() {
        //when
        memeService.deleteMeme(meme2);
        //then
        verify(memeRepositoryMock, times(1)).deleteById(meme2.getId());
    }


}