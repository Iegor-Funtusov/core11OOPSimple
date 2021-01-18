package ua.com.alevel.crud;

import ua.com.alevel.crud.entity.Role;
import ua.com.alevel.crud.entity.User;
import ua.com.alevel.crud.service.UserService;
import ua.com.alevel.security.LoginProcess;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main.main");
        User user = new User(Role.ADMIN);
        user.setLogin("admin");
        user.setPassword("nimda");
        UserService userService = new UserService();
        userService.create(user);
        String token = LoginProcess.login(user.getLogin(), user.getPassword());
        System.out.println("auth = " + LoginProcess.isAuthenticated(token));
        List<User> users = userService.findAll();
        System.out.println("users = " + users.size());
    }
}
