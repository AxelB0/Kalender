package be.intecbrussel.kalender.controllers.MVC;

import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.services.interfaces.FriendshipService;
import be.intecbrussel.kalender.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
class BasicController {

    private final UserService userService;
    private final FriendshipService friendshipService;

    @Autowired
    public BasicController(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
    }

    @GetMapping("/")
    public String hello() {
        return "placeholder";
    }

    @PostMapping(value = "/create", params = "create")
    public String createUser(User userReceived) {
        userService.createUser(userReceived);
        return "redirect:/";
    }

    @PostMapping(value = "/create", params = "friendPage")
    public String friendPage() {
        return "redirect:/friendPage";
    }


    @GetMapping("/friendPage")
    public String friendPage(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUsername") == null) {
            return "index";
        }

        List<User> friends = userService.getFriendsFromUser(httpSession.getAttribute("loggedInUsername").toString());
//        System.out.println(httpSession.getAttribute("loggedInUsername").toString());
        model.addAttribute("friends", friends);

        List<User> notFriends = userService.getNotFriendsFromUser(httpSession.getAttribute("loggedInUsername").toString(), friends);
        model.addAttribute("notFriends", notFriends);

//        System.out.println(friends);
//        System.out.println(notFriends);
//
//        for (User user : friends) {
//            System.out.println("VERY YES FRIEND: " + user);
//        }
//
//        for (User notFriend : notFriends) {
//            System.out.println("NO NO NO FRIEND: " + notFriend);
//        }

        return "friendPage";
    }


    @PostMapping(value = "/friendPage/create")
    public String createFriendship(HttpSession httpSession,@RequestParam(value = "friendID") int userID) {
        Optional<User> user1 = userService.findUserByUsername(httpSession.getAttribute("loggedInUsername").toString());
        Optional<User> user2 = userService.findUserByID(userID);
        if (user1.isPresent() && user2.isPresent()){
            friendshipService.create(user1.get(),user2.get());
        }
        return "redirect:/friendPage";
    }

    @GetMapping("/login")
    public String login(HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUsername") == null) {
            return "index";
        }
        return "redirect:/friendPage";
    }

    @PostMapping(value = "/send")
    public String login(User userReceived, HttpSession httpSession) {
//        System.out.println(userReceived);

        if (userService.getUserByUsernameAndPassword(userReceived.getUsername(), userReceived.getPassword()).isPresent()) {
            httpSession.setAttribute("loggedInUsername", userReceived.getUsername());
            return "redirect:/friendPage";
        }

        return "redirect:/login";
    }


}
