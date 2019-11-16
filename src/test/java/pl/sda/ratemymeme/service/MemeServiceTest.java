package pl.sda.ratemymeme.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.MemeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class MemeServiceTest {
    private Role roleUser = new Role("USER");
    private User user1 = new User("User1", "user@gmail.com", "password", LocalDate.now(), roleUser);
    private Meme meme1 = new Meme("Meme1", LocalDateTime.now(), "memebase/kiedys-to-byli-programisci.jpg");
    private Meme meme2 = new Meme("Meme2", LocalDateTime.now(), "memebase/HehehelloMEME.jpg");
    private Meme meme3 = new Meme("Meme3", LocalDateTime.now(), "memebase/66QC917gOTDokD7R.jpg");


    private UserService getUserServiceMock() {
        UserService userServiceMock = mock(UserService.class);
        when(userServiceMock.findByLogin("user1")).thenReturn(user1);
        return userServiceMock;
    }

    private VoteService getVoteServiceMock() {
        VoteService voteServiceMock = mock(VoteService.class);
        return voteServiceMock;
    }

    private MemeRepository getMemeRepositoryMock(){
        MemeRepository memeRepository = mock(MemeRepository.class);
        when(memeRepository.save(meme3)).thenReturn(meme3);
        return memeRepository;
    }

    private MemeService memeService = new MemeService(getMemeRepositoryMock(), getUserServiceMock(), getVoteServiceMock());

    @BeforeEach
    public void init(){
        SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication("user1"));
    }

    @Test
    public void shouldAddMemeToDataBase() {
        //When
        Meme meme = memeService.addMemeToDataBase(meme3.getNameMeme(), meme3.getSourceAdress());
        //then
        assertThat(meme).isEqualTo(meme3);
    }

    @Test
    public void shouldGetMemeById(){

    }




}