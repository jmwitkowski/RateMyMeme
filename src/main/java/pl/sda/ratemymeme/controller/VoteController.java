package pl.sda.ratemymeme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.service.MemeService;
import pl.sda.ratemymeme.service.VoteService;

@Controller
public class VoteController {
    private final VoteService voteService;
    private final MemeService memeService;


    public VoteController(VoteService voteService, MemeService memeService) {
        this.voteService = voteService;
        this.memeService = memeService;
    }

    @GetMapping("/votePlus/{memeId}")
    public String votePlus(@PathVariable int memeId){
        Meme meme = memeService.getMemeById(memeId);
        voteService.votePlus(meme);
        return "redirect:/";
    }
}
