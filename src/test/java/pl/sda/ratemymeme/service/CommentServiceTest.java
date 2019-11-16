package pl.sda.ratemymeme.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.sda.ratemymeme.model.Comment;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.CommentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    private Role roleUser = new Role("USER");
    private User user1 = new User("user", "user@gmail.com", "password", LocalDate.now(), roleUser);
    private Meme meme1 = new Meme("Meme1", LocalDateTime.now(), "memebase/kiedys-to-byli-programisci.jpg");
    private Comment comment = new Comment(user1,meme1,"Kubus maly pysiu",LocalDateTime.now());
    private Comment comment2 = new Comment(user1,meme1,"Second comment",LocalDateTime.now());

        CommentRepository commentRepository = mock(CommentRepository.class);
        MemeService memeService = mock(MemeService.class);
        UserService userService = mock(UserService.class);


    CommentService commentService = new CommentService(commentRepository,memeService,userService);

    @BeforeEach
    public void init(){
        SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication("user"));
    }

    @Test
    public void shouldAddCommentToDataBase() {
        when(memeService.getMemeById(1)).thenReturn(meme1);
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment commentExpected = commentService.addComment(meme1.getId(), "Kubus maly pysiu");

        assertThat(commentExpected).isEqualTo(comment);
    }

    @Test
    public void shouldDisplayAllCommentForMeme() {
        // given
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        commentList.add(comment2);
        when(commentRepository.findAllByMeme_Id(1)).thenReturn(commentList);

        // when
        List<Comment> allCommentForMeme = commentService.getAllCommentForMeme(1);
        // then
        assertThat(allCommentForMeme).isEqualTo(commentList);
    }
}