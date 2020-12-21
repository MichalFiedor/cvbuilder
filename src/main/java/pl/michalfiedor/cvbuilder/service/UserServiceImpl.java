package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.exception.UserAlreadyExistException;
import pl.michalfiedor.cvbuilder.model.Role;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;


    public void registerNewUserAccount(User user) throws  UserAlreadyExistException{
        if(emailExist(user.getEmail())){
            throw new UserAlreadyExistException("Account with email : +" + user.getEmail() + "already exists");
        }
        Role role = roleService.findRoleByName("ROLE_USER");
        user.addRole(role);
        userRepository.save(user);
    }

    public User getUser(String userEmail){
        return userRepository.findByEmail(userEmail);
    }

    public void save(User user){
        userRepository.save(user);
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email)!=null;
    }



}
