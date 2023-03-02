package be.intecbrussel.kalender.services.interfaces;

import be.intecbrussel.kalender.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByID(int id);
    void createUser(User user);
//    List<User> getFriendsByBirthMonth(User user, int birthMonth);
    Optional<User> getUserByUsernameAndPassword(String username, String password);
    List<User> getFriendsFromUser(String username);
    List<User> getNotFriendsFromUser(String username, List<User> friends);
    Optional<User> findUserByUsername(String username);

    List<User> findAll();


}
