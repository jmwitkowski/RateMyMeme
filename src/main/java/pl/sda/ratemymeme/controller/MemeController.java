package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.service.MemeService;
import pl.sda.ratemymeme.service.StorageService;


@Controller
public class MemeController {
    private final MemeService memeService;
    private final StorageService storageService;

    @Autowired
    public MemeController(MemeService memeService, StorageService storageService) {
        this.memeService = memeService;
        this.storageService = storageService;
    }

    @PostMapping(value = "/uploadmeme", consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.ALL_VALUE})
    public ModelAndView addMeme(@RequestPart("file") MultipartFile file, @RequestPart("memeName") String memeName) {
        storageService.store(file);
        String pathOfFile = storageService.getMemePath(file);
        memeService.addMemeToDataBase(memeName,pathOfFile);
        ModelAndView modelAndView = new ModelAndView("good");
        modelAndView.addObject("message", "Meme added :) ");
        return modelAndView;
    }

    @GetMapping(value = "/formMeme")
    public ModelAndView getFormMemePage(){
    return new ModelAndView("formMeme");}


}
