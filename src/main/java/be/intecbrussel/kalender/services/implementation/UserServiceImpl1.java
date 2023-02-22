package be.intecbrussel.kalender.services.implementation;

import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.repositories.UserRepository;
import be.intecbrussel.kalender.services.interfaces.FriendshipService;
import be.intecbrussel.kalender.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl1 implements UserService {

    private final UserRepository userRepository;
    private final FriendshipService friendshipService;


    @Autowired
    public UserServiceImpl1(UserRepository userRepository, FriendshipService friendshipService) {
        this.userRepository = userRepository;
        this.friendshipService = friendshipService;
    }

    public Optional<User> findUserByID(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

//    @Override
//    @Query(value = "SELECT u from User.friendship u inner join Friendship f on User.userID= f.friendshipID")
//    public List<User> getFriendsByBirthMonth(User user, int birthMonth) {
//        user.getFriendship();
//
//        return null;
//    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        return Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
    }

    public List<User> getFriendsFromUser(String username) {
        Optional<User> user = findUserByUsername(username);
        if (user.isPresent()) {
            return friendshipService.getFriendsFromUser(user.get());
        }
        return null;
    }

    public List<User> getNotFriendsFromUser(String username, List<User> friends) {
        List<User> allUsers = userRepository.findAll();
        allUsers.remove(userRepository.findByUsername(username));
        allUsers.removeAll(friends);
        return allUsers;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

}
