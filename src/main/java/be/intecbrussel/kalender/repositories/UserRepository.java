package be.intecbrussel.kalender.repositories;

import be.intecbrussel.kalender.entities.Friendship;
import be.intecbrussel.kalender.entities.User;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query(value = "select * from User u where BINARY u.username = ?1 and BINARY u.password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

}
