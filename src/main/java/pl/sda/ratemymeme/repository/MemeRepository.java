package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Meme;

import java.util.List;

public interface MemeRepository extends JpaRepository<Meme, Integer> {

}
