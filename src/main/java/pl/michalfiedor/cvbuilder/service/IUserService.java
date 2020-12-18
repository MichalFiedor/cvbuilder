package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.exception.UserAlreadyExistException;
import pl.michalfiedor.cvbuilder.model.User;

public interface IUserService {

    void registerNewUserAccount(User user) throws UserAlreadyExistException;

    User getUser(String userEmail);

    void save(User user);
}
