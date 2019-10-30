package pl.sda.ratemymeme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(value = "/uploadmeme")
    public ResponseEntity<?> addMeme(@RequestParam("file") MultipartFile file){
        storageService.store(file);
        return ResponseEntity.ok("Meme added");
    }

}
