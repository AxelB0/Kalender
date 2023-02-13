package be.intecbrussel.kalender.controllers.MVC;

import be.intecbrussel.kalender.entities.User;
import be.intecbrussel.kalender.services.implementation.FriendshipServiceImpl1;
import be.intecbrussel.kalender.services.implementation.UserServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
class BasicController {

    UserServiceImpl1 userServiceImpl1;
    FriendshipServiceImpl1 friendshipServiceImpl1;

    @Autowired
    public BasicController(UserServiceImpl1 userServiceImpl1, FriendshipServiceImpl1 friendshipServiceImpl1){
        this.userServiceImpl1 = userServiceImpl1;
        this.friendshipServiceImpl1 = friendshipServiceImpl1;
    }

    @GetMapping("/")
    public String hello(){
        return "placeholder";
    }

    @PostMapping(value = "/create", params = "create")
    public String login(User userReceived){
        userServiceImpl1.createUser(userReceived);
        return "redirect:/";
    }

    @PostMapping(value = "/create", params = "friendPage")
    public String friendpage(){
        return "redirect:/friendPage";
    }

    @GetMapping("/friendPage")
    public String friendPage(Model model){
        List<User> users = userServiceImpl1.findAllUsers();
        model.addAttribute("users", users);

        return "friendPage";
    }


    @PostMapping(value = "/friendPage/create")
    public String createFriendship(int id1, int id2){
        Optional<User> user1 = userServiceImpl1.findUserByID(id1);
        Optional<User> user2 = userServiceImpl1.findUserByID(id2);
        if (user1.isEmpty()||user2.isEmpty()){
            //TODO

            return "redirect:/friendPage";
        }

        friendshipServiceImpl1.create(user1.get(), user2.get());
        return "redirect:/friendPage";
    }

    @GetMapping("/login")
    public String login(){
        return "/login/index";
    }



}
