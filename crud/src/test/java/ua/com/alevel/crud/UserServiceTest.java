package ua.com.alevel.crud;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.crud.entity.User;
import ua.com.alevel.crud.service.UserService;
import ua.com.alevel.security.LoginProcess;

import java.util.List;

import static ua.com.alevel.crud.TestUtil.getAdminUser;

public class UserServiceTest {

    UserService userService = new UserService();

    @Test
    @Order(1)
    public void create() {
        User user = getAdminUser();
        userService.create(user);
        String token = LoginProcess.login(user.getLogin(), user.getPassword());
        System.out.println("token = " + token);
        boolean auth = LoginProcess.isAuthenticated(token);
        System.out.println("auth = " + auth);
        List<User> users = userService.findAll();
        System.out.println("users = " + users);
        Assert.assertEquals(users.size(), 1);
    }
}
