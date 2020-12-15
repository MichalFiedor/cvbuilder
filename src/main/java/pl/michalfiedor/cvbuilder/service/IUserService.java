package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.exception.UserAlreadyExistException;
import pl.michalfiedor.cvbuilder.model.User;

public interface IUserService {

    public void registerNewUserAccount(User user) throws UserAlreadyExistException;

    public User getUser(String userEmail);

    public void save(User user);
}
