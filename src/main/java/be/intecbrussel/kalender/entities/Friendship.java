package be.intecbrussel.kalender.entities;


import jakarta.persistence.*;


@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int friendshipID;


    @ManyToOne
    private User friendshipOwner;

    @ManyToOne
    private User friend;

    private int date;

    protected Friendship() {
    }

    public Friendship(User friendshipOwner, User friend) {
        this.friendshipOwner = friendshipOwner;
        this.friend = friend;
    }

    public int getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(int friendshipID) {
        this.friendshipID = friendshipID;
    }

    public User getFriendshipOwner() {
        return friendshipOwner;
    }

    public void setFriendshipOwner(User friendshipOwner) {
        this.friendshipOwner = friendshipOwner;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

}
