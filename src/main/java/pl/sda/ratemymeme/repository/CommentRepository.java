package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
