package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Meme;

public interface MemeRepository extends JpaRepository<Meme, Integer> {
}
