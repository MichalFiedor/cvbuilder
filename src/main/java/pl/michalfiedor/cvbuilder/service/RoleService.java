package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Role;
import pl.michalfiedor.cvbuilder.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
