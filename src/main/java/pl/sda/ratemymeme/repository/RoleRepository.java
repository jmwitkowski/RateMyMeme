package pl.sda.ratemymeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.ratemymeme.model.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
