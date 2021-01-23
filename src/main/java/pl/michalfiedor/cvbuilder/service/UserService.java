package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.exception.UserAlreadyExistException;
import pl.michalfiedor.cvbuilder.model.Role;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.RoleService;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;


    public void registerNewAccount(User user) throws  UserAlreadyExistException{
        if(emailExist(user.getEmail())){
            throw new UserAlreadyExistException("Account with email : +" + user.getEmail() + "already exists");
        }
        Role role = roleService.findRoleByName("ROLE_USER");
        user.addRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User get(String userEmail){
        return userRepository.findByEmail(userEmail);
    }

    public void save(User user){
        userRepository.save(user);
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email)!=null;
    }



}
