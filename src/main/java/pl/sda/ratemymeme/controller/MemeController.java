package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.exception.MemeNotFoundException;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.service.CommentService;
import pl.sda.ratemymeme.service.MemeService;
import pl.sda.ratemymeme.service.StorageService;


@Controller
public class MemeController {
    private final MemeService memeService;
    private final StorageService storageService;
    private final CommentService commentService;

    @Autowired
    public MemeController(MemeService memeService, StorageService storageService, CommentService commentService) {
        this.memeService = memeService;
        this.storageService = storageService;
        this.commentService = commentService;
    }

    @PostMapping(value = "/uploadmeme", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.ALL_VALUE})
    public ModelAndView addMeme(@RequestPart("file") MultipartFile file, @RequestPart("memeName") String memeName) {
        storageService.store(file);
        String pathOfFile = storageService.getMemePath(file);
        memeService.addMemeToDataBase(memeName, pathOfFile);
        ModelAndView modelAndView = new ModelAndView("good");
        modelAndView.addObject("message", "Meme added :) ");
        return modelAndView;
    }

    @GetMapping(value = "/formMeme")
    public ModelAndView getFormMemePage() {
        return new ModelAndView("formMeme");
    }


    @GetMapping(value = "/meme/{id}")
    public ModelAndView getSingleMemePage(@PathVariable int id) throws MemeNotFoundException {
        Meme meme = memeService.getMemeById(id);
        ModelAndView modelAndView = new ModelAndView("meme");
        modelAndView.addObject("memeWithVote", memeService.getMemeWithVote(meme));
        modelAndView.addObject("activeUserName", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("listOfComments", commentService.getAllCommentForMeme(id));
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteMemeFromWebsite(@PathVariable int id) {
        Meme meme = memeService.getMemeById(id);
        memeService.deleteMeme(meme);
        return "redirect:/";
    }
}
