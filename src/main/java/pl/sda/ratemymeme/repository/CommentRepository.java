package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByMeme_Id(int memeId);
}
