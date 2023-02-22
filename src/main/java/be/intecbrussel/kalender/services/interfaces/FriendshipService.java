package be.intecbrussel.kalender.services.interfaces;

import be.intecbrussel.kalender.entities.User;

import java.util.List;

public interface FriendshipService {

    void create(User friendshipOwner, User friend);
    List<User> getFriendsFromUser(User user);
}
