package pl.sda.ratemymeme.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Comment;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.CommentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemeService memeService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, MemeService memeService, UserService userService) {
        this.commentRepository = commentRepository;
        this.memeService = memeService;
        this.userService = userService;
    }


    public void addComment(int memeID, String content) {
        User activeUser = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Meme meme = memeService.getMemeById(memeID);
        String parsedContent = content.substring(8);
        Comment comment = new Comment(activeUser,meme,parsedContent, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        commentRepository.save(comment);
    }
}
