package pl.sda.ratemymeme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.service.CommentService;
import pl.sda.ratemymeme.service.MemeService;


@Controller
public class ComentController {

    private final CommentService commentService;
    private final MemeService memeService;

    public ComentController(CommentService commentService, MemeService memeService) {
        this.commentService = commentService;
        this.memeService = memeService;
    }


    @PostMapping(value = "/addComment/{memeId}")
    public String addComment(@PathVariable int memeId, @RequestParam String content) {
        Meme meme = memeService.getMemeById(memeId);
        commentService.addComment(memeId,content);
        return "redirect:/meme/" + memeId;
    }
}
