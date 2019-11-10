package pl.sda.ratemymeme.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.model.Comment;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.repository.CommentRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
        Comment comment = new Comment(activeUser,meme,content, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        commentRepository.save(comment);
    }

    public List<Comment> getAllCommentForMeme(int memeId){
        return commentRepository.findAllByMeme_Id(memeId);
    }
}
