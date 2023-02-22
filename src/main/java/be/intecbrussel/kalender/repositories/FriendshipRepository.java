package be.intecbrussel.kalender.repositories;

import be.intecbrussel.kalender.entities.Friendship;
import be.intecbrussel.kalender.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

    List<Friendship> findAllByFriendOrFriendshipOwner(User user, User sameUser);
    List<Friendship> findAllByFriendAndFriendshipOwner(User friend, User friendshipOwner);
}
