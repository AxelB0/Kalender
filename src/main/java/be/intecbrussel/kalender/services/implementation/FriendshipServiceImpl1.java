package be.intecbrussel.kalender.services.implementation;

import be.intecbrussel.kalender.entities.Friendship;
import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.repositories.FriendshipRepository;
import be.intecbrussel.kalender.services.interfaces.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl1 implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    @Autowired
    public FriendshipServiceImpl1(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public void create(User friendshipOwner, User friend) {
        if (friendshipRepository.findAllByFriendAndFriendshipOwner(friend, friendshipOwner).isEmpty() && friendshipRepository.findAllByFriendAndFriendshipOwner(friendshipOwner, friend).isEmpty()){
            friendshipRepository.save(new Friendship(friendshipOwner, friend));
            return;
        }
        System.out.println("Already friends");
//        List<Friendship> friends = friendshipOwner.getFriendship();
//        for (Friendship friendship : friends) {
//            if (friend.equals(friendship.getFriend()) || friend.equals(friendship.getFriendshipOwner())) {
//                System.out.println("Already friends");
//                return;
//            }
//        }
//        friendshipRepository.save(new Friendship(friendshipOwner, friend));
    }

    public List<User> getFriendsFromUser(User user) {
        List<Friendship> friendships = friendshipRepository.findAllByFriendOrFriendshipOwner(user, user);
        System.out.println("goed " + (long) friendships.size());

        List<User> friends = new ArrayList<>();

        for (Friendship friendship : friendships) {
            if (friendship.getFriendshipOwner().equals(user))
                friends.add(friendship.getFriend());
            else
                friends.add(friendship.getFriendshipOwner());

//            System.out.println(friendship.getFriendshipOwner().getUsername());
//            if (user.equals(friendship.getFriendshipOwner())) {
//                friends.add(friendship.getFriend());
//            } else {
//                friends.add(friendship.getFriendshipOwner());
//            }
        }
        for (User friend : friends) {
            System.out.println(friend);
        }
        return friends;
    }
}
