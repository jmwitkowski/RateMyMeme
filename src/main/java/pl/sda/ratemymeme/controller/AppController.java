package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.service.FileSystemStorageService;
import pl.sda.ratemymeme.service.MemeService;

@Controller
public class AppController {
    private final MemeService memeService;
    private final FileSystemStorageService FileSystemStorageService;

    @Autowired
    public AppController(MemeService memeService, FileSystemStorageService fileSystemStorageService) {
        this.memeService = memeService;
        FileSystemStorageService = fileSystemStorageService;
    }

    @GetMapping("/")
    public ModelAndView homeView() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("memes", memeService.getAllMemes());
        modelAndView.addObject("activeUserName", SecurityContextHolder.getContext().getAuthentication().getName());
        return modelAndView;
    }

}