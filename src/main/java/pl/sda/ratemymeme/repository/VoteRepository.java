package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
