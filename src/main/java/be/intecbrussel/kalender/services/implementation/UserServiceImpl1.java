package be.intecbrussel.kalender.services.implementation;

import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.repositories.UserRepository;
import be.intecbrussel.kalender.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl1 implements UserService {

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl1(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByID(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersByBirthMonth(int birthMonth) {
        return null;
    }

    @Override
    @Query(value = "SELECT u from User.friendship u inner join Friendship f on User.userID= f.friendshipID")
    public List<User> getFriendsByBirthMonth(User user, int birthMonth) {
        user.getFriendship();

        return null;
    }


}
