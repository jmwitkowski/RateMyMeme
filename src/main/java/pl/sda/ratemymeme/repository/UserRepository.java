package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
