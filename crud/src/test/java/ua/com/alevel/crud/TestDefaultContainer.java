package ua.com.alevel.crud;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ua.com.alevel.core.ApplicationConfig;
import ua.com.alevel.core.CrudContainer;
import ua.com.alevel.crud.entity.Role;
import ua.com.alevel.crud.entity.User;
import ua.com.alevel.crud.service.UserService;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDefaultContainer {

    private final CrudContainer<User> defaultContainer = ApplicationConfig.getInstance().getContainer();
    private static final String LOGIN = "admin1";

    @Test
    @Order(1)
    public void create() {
        User user = getAdminUser();
        defaultContainer.create(user);
        List<User> users = defaultContainer.findAll();
        System.out.println("users = " + users);
        Assert.assertEquals(users.size(), 1);
    }

    @Test
    @Order(2)
    public void update() {
        List<User> users = defaultContainer.findAll();
        Assert.assertEquals(users.size(), 1);
        User user = users.get(0);
        user.setLogin(LOGIN);
        defaultContainer.update(user);
        user = defaultContainer.findById(1);
        Assert.assertEquals(user.getLogin(), LOGIN);
    }

    private User getAdminUser() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("nimda");
        user.setRole(Role.ADMIN);
        return user;
    }
}
