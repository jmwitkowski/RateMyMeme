package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Meme;
import pl.sda.ratemymeme.model.User;
import pl.sda.ratemymeme.model.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    List<Vote> findByMeme_Id(int MemeId);
}
