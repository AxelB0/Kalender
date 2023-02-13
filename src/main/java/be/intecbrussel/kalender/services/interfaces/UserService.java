package be.intecbrussel.kalender.services.interfaces;

import be.intecbrussel.kalender.entities.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    List<User> getUsersByBirthMonth(int birthMonth);

    List<User> getFriendsByBirthMonth(User user, int birthMonth);

}
