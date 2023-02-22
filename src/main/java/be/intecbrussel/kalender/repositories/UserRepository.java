package be.intecbrussel.kalender.repositories;

import be.intecbrussel.kalender.entities.Friendship;
import be.intecbrussel.kalender.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);

}
