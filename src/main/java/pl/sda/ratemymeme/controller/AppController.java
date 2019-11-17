package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.ratemymeme.service.FileSystemStorageService;
import pl.sda.ratemymeme.service.MemeService;
import pl.sda.ratemymeme.service.VoteService;

@Controller
public class AppController {
    private final MemeService memeService;
    private final FileSystemStorageService FileSystemStorageService;
    private final VoteService voteService;

    @Autowired
    public AppController(MemeService memeService, FileSystemStorageService fileSystemStorageService, VoteService voteService) {
        this.memeService = memeService;
        FileSystemStorageService = fileSystemStorageService;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public ModelAndView homeView() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("memes", memeService.getAllMemesPaginated(1));
        modelAndView.addObject("activeUserName", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("pageindex",1);
        modelAndView.addObject("islastpage",memeService.isLastPage(1));
        return modelAndView;
    }
    //Pagination
    @GetMapping("/page/{page}")
    public ModelAndView nextPageView(@PathVariable int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("memes", memeService.getAllMemesPaginated(page));
        modelAndView.addObject("activeUserName", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("pageindex",page);
        modelAndView.addObject("islastpage",memeService.isLastPage(page));
        return modelAndView;
    }

    @GetMapping("/page/1")
    public String redirectPageOneToHomePage(){
        return "redirect:/";
    }


    @GetMapping("/accessDenied")
    public ModelAndView accesDeniedView(){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", "Access denied");
        return modelAndView;
    }

    @GetMapping("/loginFailed")
    public ModelAndView loginFailedView(){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", "Wrong login or password");
        return modelAndView;
    }

}