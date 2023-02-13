package be.intecbrussel.kalender.services.implementation;

import be.intecbrussel.kalender.entities.Friendship;
import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl1 {

    private FriendshipRepository friendshipRepository;

    @Autowired
    public FriendshipServiceImpl1(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public void create(User friendshipOwner, User friend) {
        List<Friendship> friends = friendshipOwner.getFriendship();
        for (Friendship friendship : friends) {
            if (friend.equals(friendship.getFriend()) || friend.equals(friendship.getFriendshipOwner())) {
                System.out.println("Already friends");
                return;
            }
        }

        Friendship friendship = new Friendship(friendshipOwner, friend);
        friendshipRepository.save(friendship);
    }

    public List<User> filterFriends(User user) {
        Integer userID = user.getUserID();
        List<Friendship> friendships = user.getFriendship();
        List<User> friends = new ArrayList<User>();

        for (Friendship friendship : friendships) {
            if (user.equals(friendship.getFriendshipOwner())) {
                friends.add(friendship.getFriend());
            } else {
                friends.add(friendship.getFriendshipOwner());
            }
        }
        return friends;

    }
}
