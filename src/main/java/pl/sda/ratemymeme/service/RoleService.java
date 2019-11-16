package pl.sda.ratemymeme.service;

import org.springframework.stereotype.Service;
import pl.sda.ratemymeme.exception.RoleNotFoundException;
import pl.sda.ratemymeme.model.Role;
import pl.sda.ratemymeme.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(int id) {
        Optional<Role> optRole = roleRepository.findById(id);
        if(!optRole.isPresent()){
            throw  new RoleNotFoundException("Could not find role with roleID " + id);
        }
        return optRole.get();
    }
}
